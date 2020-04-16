package ru.vitalysizov.moviedb.presentation.search_tab.di

import dagger.Component
import ru.vitalysizov.moviedb.presentation.search_tab.dependencies.SearchTabDependencies
import ru.vitalysizov.moviedb.presentation.search_tab.view.SearchTabFragment

@Component(dependencies = [SearchTabDependencies::class])
interface SearchTabComponent {

    fun inject(fragment: SearchTabFragment)
}