package ru.vitalysizov.moviedb.di.modules

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.vitalysizov.moviedb.di.base.ComponentDependencies
import ru.vitalysizov.moviedb.di.base.ComponentDependenciesKey
import ru.vitalysizov.moviedb.di.components.AppComponent
import ru.vitalysizov.moviedb.presentation.account_tab.dependencies.AccountTabDependencies
import ru.vitalysizov.moviedb.presentation.home_tab.dependencies.HomeTabDependencies
import ru.vitalysizov.moviedb.presentation.main.dependencies.MainDependencies
import ru.vitalysizov.moviedb.presentation.search_tab.dependencies.SearchTabDependencies
import ru.vitalysizov.moviedb.presentation.splash.dependencies.SplashDependencies

@Module
abstract class ComponentDependenciesModule {

    @Binds
    @IntoMap
    @ComponentDependenciesKey(MainDependencies::class)
    abstract fun provideMainDependencies(appComponent: AppComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(HomeTabDependencies::class)
    abstract fun provideHomeTabDependencies(appComponent: AppComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(SearchTabDependencies::class)
    abstract fun provideSearchTabDependencies(appComponent: AppComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(AccountTabDependencies::class)
    abstract fun provideAccountTabDependencies(appComponent: AppComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(SplashDependencies::class)
    abstract fun provideSplashDependencies(appComponent: AppComponent): ComponentDependencies

}