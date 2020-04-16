package ru.vitalysizov.moviedb.presentation.account_tab.di

import dagger.Component
import ru.vitalysizov.moviedb.presentation.account_tab.dependencies.AccountTabDependencies
import ru.vitalysizov.moviedb.presentation.account_tab.view.AccountTabFragment

@Component(dependencies = [AccountTabDependencies::class])
interface AccountTabComponent {

    fun inject(fragment: AccountTabFragment)

}