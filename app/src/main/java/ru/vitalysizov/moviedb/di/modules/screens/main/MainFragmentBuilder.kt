package ru.vitalysizov.moviedb.di.modules.screens.main

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import ru.vitalysizov.moviedb.di.modules.ViewModelKey
import ru.vitalysizov.moviedb.presentation.account_tab.view.AccountTabFragment
import ru.vitalysizov.moviedb.presentation.account_tab.viewmodel.AccountTabViewModel
import ru.vitalysizov.moviedb.presentation.home_tab.view.HomeTabFragment
import ru.vitalysizov.moviedb.presentation.home_tab.viewmodel.HomeTabViewModel
import ru.vitalysizov.moviedb.presentation.search_tab.view.SearchTabFragment
import ru.vitalysizov.moviedb.presentation.search_tab.viewmodel.SearchTabViewModel

@Module
interface MainFragmentBuilder {

    @ContributesAndroidInjector
    fun contributeHomeTabFragment(): HomeTabFragment

    @ContributesAndroidInjector
    fun contributeSearchTabFragment(): SearchTabFragment

    @ContributesAndroidInjector
    fun contributeAccountTabFragment(): AccountTabFragment

    @Binds
    @IntoMap
    @ViewModelKey(HomeTabViewModel::class)
    fun bindHomeTabViewModel(viewModel: HomeTabViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchTabViewModel::class)
    fun bindSearchTabViewModel(viewModel: SearchTabViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AccountTabViewModel::class)
    fun bindAccountTabViewModel(viewModel: AccountTabViewModel): ViewModel

}