package ru.vitalysizov.moviedb.di.modules.screens.authentication

import dagger.Module
import dagger.Provides
import ru.vitalysizov.moviedb.presentation.authentication.view.AuthenticationFragment
import ru.vitalysizov.moviedb.presentation.authentication.view.AuthenticationFragmentArgs
import javax.inject.Named

@Module
class AuthenticationModule {

    object Params {
        const val AUTH_URL = "auth_url"
    }

    @Provides
    @Named(AuthenticationModule.Params.AUTH_URL)
    fun provideRequestToken(fragment: AuthenticationFragment): String {
        return AuthenticationFragmentArgs.fromBundle(fragment.requireArguments()).authUrl
    }
}