package ru.vitalysizov.moviedb.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.vitalysizov.moviedb.di.modules.screens.MainFragmentBuilder
import ru.vitalysizov.moviedb.presentation.main.view.MainFragment

@Module
interface FragmentBuilder {

    @ContributesAndroidInjector(modules = [MainFragmentBuilder::class])
    fun contributeMainFragment(): MainFragment

}