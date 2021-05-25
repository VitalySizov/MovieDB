package ru.vitalysizov.moviedb.di.modules.screens.authentication

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.vitalysizov.moviedb.di.modules.ViewModelKey
import ru.vitalysizov.moviedb.presentation.authentication.viewmodel.AuthenticationViewModel

@Module
interface AuthenticationFragmentBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(AuthenticationViewModel::class)
    fun bindAuthenticationViewModel(viewModel: AuthenticationViewModel): ViewModel
}