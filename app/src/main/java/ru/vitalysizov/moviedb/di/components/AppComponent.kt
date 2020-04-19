package ru.vitalysizov.moviedb.di.components

import dagger.Component
import ru.vitalysizov.moviedb.MovieDbApplication
import ru.vitalysizov.moviedb.di.modules.*
import ru.vitalysizov.moviedb.presentation.AppActivity
import ru.vitalysizov.moviedb.presentation.account_tab.dependencies.AccountTabDependencies
import ru.vitalysizov.moviedb.presentation.home_tab.dependencies.HomeTabDependencies
import ru.vitalysizov.moviedb.presentation.main.dependencies.MainDependencies
import ru.vitalysizov.moviedb.presentation.search_tab.dependencies.SearchTabDependencies
import ru.vitalysizov.moviedb.presentation.splash.dependencies.SplashDependencies
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        ComponentDependenciesModule::class,
        NetworkModule::class,
        RepositoriesModule::class,
        UtilsModule::class
    ]
)

@Singleton
interface AppComponent : MainDependencies, HomeTabDependencies, SearchTabDependencies,
    AccountTabDependencies, SplashDependencies {

    fun inject(appActivity: AppActivity)
    fun inject(application: MovieDbApplication)

}