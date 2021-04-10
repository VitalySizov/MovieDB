package ru.vitalysizov.moviedb.di.modules.screens.splash

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.vitalysizov.moviedb.di.modules.ViewModelKey
import ru.vitalysizov.moviedb.presentation.splash.viewmodel.SplashViewModel

@Module
interface SplashFragmentBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel
}