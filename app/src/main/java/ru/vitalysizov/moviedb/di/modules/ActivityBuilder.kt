package ru.vitalysizov.moviedb.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import ru.vitalysizov.moviedb.presentation.activity.AppActivity
import ru.vitalysizov.moviedb.presentation.main.viewmodel.MainViewModel

@Module
interface ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            ActivityModule::class,
            FragmentBuilder::class
        ]
    )
    fun contributeAppActivity(): AppActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindUserViewModel(viewModel: MainViewModel): ViewModel
}