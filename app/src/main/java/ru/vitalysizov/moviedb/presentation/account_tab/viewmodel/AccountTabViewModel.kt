package ru.vitalysizov.moviedb.presentation.account_tab.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.vitalysizov.moviedb.BuildConfig
import ru.vitalysizov.moviedb.data.local.prefs.MovieDbPreferences
import ru.vitalysizov.moviedb.domain.params.authentication.CreateSessionParams
import ru.vitalysizov.moviedb.domain.params.authentication.DeleteSessionParams
import ru.vitalysizov.moviedb.domain.useCase.account.GetAccountDetailsUseCase
import ru.vitalysizov.moviedb.domain.useCase.authentication.CreateRequestTokenUseCase
import ru.vitalysizov.moviedb.domain.useCase.authentication.CreateSessionUseCase
import ru.vitalysizov.moviedb.domain.useCase.authentication.LogoutAccountUseCase
import ru.vitalysizov.moviedb.model.domain.account.AccountDetailsItem
import ru.vitalysizov.moviedb.presentation.base.viewmodel.BaseViewModel
import ru.vitalysizov.moviedb.utils.Event
import ru.vitalysizov.moviedb.utils.ioToUi
import javax.inject.Inject

class AccountTabViewModel @Inject constructor(
    private val createRequestTokenUseCase: CreateRequestTokenUseCase,
    private val createSessionUseCase: CreateSessionUseCase,
    private val getAccountDetailsUseCase: GetAccountDetailsUseCase,
    private val logoutAccountUseCase: LogoutAccountUseCase,
    private val movieDbPreferences: MovieDbPreferences
) : BaseViewModel() {

    private val authUrlMutable = MutableLiveData<Event<String>>()
    val authUrl: LiveData<Event<String>>
        get() = authUrlMutable

    private val showAuthButtonMutable = MutableLiveData<Boolean>()
    val showAuthButton: LiveData<Boolean>
        get() = showAuthButtonMutable

    private val showLogoutButtonMutable = MutableLiveData<Boolean>()
    val showLogoutButton: LiveData<Boolean>
        get() = showLogoutButtonMutable

    private val accountDetailsMutable = MutableLiveData<AccountDetailsItem>()
    val accountDetails: LiveData<AccountDetailsItem>
        get() = accountDetailsMutable

    init {
        getAccountDetails()
    }

    fun onLoginClicked() {
        launch {
            createRequestTokenUseCase.invoke()
                .ioToUi()
                .subscribe({ response ->
                    if (response.success && response.requestToken.isNotEmpty()) {
                        authUrlMutable.value = Event(
                            "${BuildConfig.TMDB_AUTH_URL}${response.requestToken}${BuildConfig.AUTH_REDIRECT}"
                        )
                    }
                }, { error ->
                    error.printStackTrace()
                })
        }
    }

    fun onCreateSessionAndGetUser(requestToken: String) {
        launch {
            createSessionUseCase.invoke(CreateSessionParams(requestToken))
                .flatMap { response ->
                    if (response.success) {
                        movieDbPreferences.sessionId = response.sessionId
                        getAccountDetailsUseCase.invoke(response.sessionId)
                    } else {
                        //TODO handle error
                        throw Exception("don't success")
                    }
                }
                .ioToUi()
                .subscribe({ accountDetails ->
                    accountDetailsMutable.value = accountDetails
                    showAuthButtonMutable.value = false
                    showLogoutButtonMutable.value = true
                }, { error ->
                    error.printStackTrace()
                })
        }
    }

    private fun getAccountDetails() {
        val sessionId = movieDbPreferences.sessionId

        if (sessionId.isNullOrEmpty()) {
            showAuthButtonMutable.value = true
            return
        }

        launch {
            getAccountDetailsUseCase.invoke(sessionId)
                .ioToUi()
                .doOnSubscribe { showLoading() }
                .doAfterTerminate { hideLoading() }
                .subscribe({ accountDetails ->
                    movieDbPreferences.account = accountDetails
                    accountDetailsMutable.value = accountDetails
                    showLogoutButtonMutable.value = true
                }, { error ->
                    error.printStackTrace()
                })
        }
    }

    fun onLogoutClicked() {
        val sessionId = movieDbPreferences.sessionId

        if (sessionId.isNullOrEmpty()) {
            return
        }

        launch {
            logoutAccountUseCase.invoke(DeleteSessionParams(sessionId))
                .ioToUi()
                .doOnSubscribe { showLoading() }
                .doAfterTerminate { hideLoading() }
                .subscribe({
                    if (it.success) {
                        movieDbPreferences.clearSessionAndAccountData()
                        accountDetailsMutable.value = movieDbPreferences.account
                        showLogoutButtonMutable.value = false
                        showAuthButtonMutable.value = true
                    }
                }, { error ->
                    error.printStackTrace()
                })
        }
    }

}