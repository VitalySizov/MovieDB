package ru.vitalysizov.moviedb.presentation.home_tab.di

import dagger.Component
import ru.vitalysizov.moviedb.presentation.home_tab.dependencies.HomeTabDependencies
import ru.vitalysizov.moviedb.presentation.home_tab.view.HomeTabFragment

@Component(dependencies = [HomeTabDependencies::class])
interface HomeTabComponent {

    fun inject(fragment: HomeTabFragment)
}