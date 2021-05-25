package ru.vitalysizov.moviedb.presentation.account_tab.viewmodel

import androidx.lifecycle.*
import ru.vitalysizov.moviedb.BuildConfig
import ru.vitalysizov.moviedb.data.local.prefs.AccountPreferences
import ru.vitalysizov.moviedb.data.repo.web.MovieDbWebRepository
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
    private val accountPreferences: AccountPreferences,
    private val movieDbWebRepository: MovieDbWebRepository
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
                .doOnSubscribe { showLoading() }
                .doAfterTerminate { hideLoading() }
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

    fun onCreateSessionAndGetAccount(requestToken: String) {
        launch {
            createSessionUseCase.invoke(CreateSessionParams(requestToken)).ioToUi()
                .doOnSubscribe { showLoading() }
                .doAfterTerminate { hideLoading() }
                .subscribe({ response ->
                    if (response.success) {
                        accountPreferences.sessionId = response.sessionId
                        getAccountDetails()
                    } else {
                        //TODO handle error
                        throw Exception("don't success")
                    }
                }, { error ->
                    error.printStackTrace()
                })
        }
    }

    private fun getAccountDetails() {
        val sessionId = accountPreferences.sessionId

        if (sessionId.isNullOrEmpty()) {
            setNotAuthorizedButtonsState()
            return
        } else {
            setAuthorizedButtonsState()
            accountDetailsMutable.value = accountPreferences.account
        }

        launch {
            getAccountDetailsUseCase.invoke(sessionId).ioToUi()
                .flatMap { accountDetails ->
                    accountPreferences.account = accountDetails
                    return@flatMap movieDbWebRepository.getAccountUrlAvatar(accountDetails.userName)
                        .ioToUi()
                }
                .doOnSubscribe { showLoading() }
                .doAfterTerminate { hideLoading() }
                .subscribe({ avatarUrl ->
                    val account = accountPreferences.account
                    account?.avatarUrl = avatarUrl
                    accountPreferences.account = account
                    accountDetailsMutable.value = account
                }, { error ->
                    error.printStackTrace()
                })
        }
    }

    fun onLogoutClicked() {
        val sessionId = accountPreferences.sessionId

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
                        accountPreferences.clearAccountPreferences()
                        accountDetailsMutable.value = accountPreferences.account
                        setNotAuthorizedButtonsState()
                    }
                }, { error ->
                    error.printStackTrace()
                })
        }
    }

    private fun setAuthorizedButtonsState() {
        showLogoutButtonMutable.value = true
        showAuthButtonMutable.value = false
    }

    private fun setNotAuthorizedButtonsState() {
        showLogoutButtonMutable.value = false
        showAuthButtonMutable.value = true
    }

}