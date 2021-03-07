package ru.vitalysizov.moviedb.di.modules.screens.settings

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.vitalysizov.moviedb.di.modules.ViewModelKey
import ru.vitalysizov.moviedb.presentation.settings.viewmodel.SettingsViewModel

@Module
interface SettingsFragmentBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel

}