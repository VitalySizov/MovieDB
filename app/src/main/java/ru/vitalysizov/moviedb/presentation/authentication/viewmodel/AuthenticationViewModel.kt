package ru.vitalysizov.moviedb.presentation.authentication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.vitalysizov.moviedb.di.modules.screens.authentication.AuthenticationModule
import ru.vitalysizov.moviedb.presentation.base.viewmodel.BaseViewModel
import ru.vitalysizov.moviedb.utils.Event
import javax.inject.Inject
import javax.inject.Named

class AuthenticationViewModel @Inject constructor(
    @Named(AuthenticationModule.Params.AUTH_URL) private val authUrlParams: String,
) : BaseViewModel() {

    private val authUrlMutable = MutableLiveData<String>()
    val authUrl: LiveData<String>
        get() = authUrlMutable

    init {
        authUrlMutable.value = authUrlParams
    }

}