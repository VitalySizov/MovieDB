package ru.vitalysizov.moviedb.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.vitalysizov.moviedb.di.modules.screens.authentication.AuthenticationFragmentBuilder
import ru.vitalysizov.moviedb.di.modules.screens.authentication.AuthenticationModule
import ru.vitalysizov.moviedb.di.modules.screens.main.MainFragmentBuilder
import ru.vitalysizov.moviedb.di.modules.screens.movie_details.MovieDetailsModule
import ru.vitalysizov.moviedb.di.modules.screens.movie_details.MoviesDetailsFragmentBuilder
import ru.vitalysizov.moviedb.di.modules.screens.person_details.PersonDetailsFragmentBuilder
import ru.vitalysizov.moviedb.di.modules.screens.person_details.PersonDetailsModule
import ru.vitalysizov.moviedb.di.modules.screens.search_result.SearchResultFragmentBuilder
import ru.vitalysizov.moviedb.di.modules.screens.search_result.SearchResultModule
import ru.vitalysizov.moviedb.di.modules.screens.settings.SettingsFragmentBuilder
import ru.vitalysizov.moviedb.di.modules.screens.splash.SplashFragmentBuilder
import ru.vitalysizov.moviedb.presentation.authentication.view.AuthenticationFragment
import ru.vitalysizov.moviedb.presentation.main.view.MainFragment
import ru.vitalysizov.moviedb.presentation.movie_details.view.MovieDetailsFragment
import ru.vitalysizov.moviedb.presentation.person_details.view.PersonDetailsFragment
import ru.vitalysizov.moviedb.presentation.search_result.viewmodel.SearchResultFragment
import ru.vitalysizov.moviedb.presentation.settings.view.SettingsFragment
import ru.vitalysizov.moviedb.presentation.splash.view.SplashFragment

@Module
interface FragmentBuilder {

    @ContributesAndroidInjector(modules = [MainFragmentBuilder::class])
    fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector(modules = [MovieDetailsModule::class, MoviesDetailsFragmentBuilder::class])
    fun contributeMovieDetailsFragment(): MovieDetailsFragment

    @ContributesAndroidInjector(modules = [SearchResultModule::class, SearchResultFragmentBuilder::class])
    fun contributeSearchResultFragment(): SearchResultFragment

    @ContributesAndroidInjector(modules = [PersonDetailsModule::class, PersonDetailsFragmentBuilder::class])
    fun contributePeopleDetailsFragment(): PersonDetailsFragment

    @ContributesAndroidInjector(modules = [AuthenticationModule::class, AuthenticationFragmentBuilder::class])
    fun contributeAuthenticationFragment(): AuthenticationFragment

    @ContributesAndroidInjector(modules = [SettingsFragmentBuilder::class])
    fun contributeSettingsFragment(): SettingsFragment

    @ContributesAndroidInjector(modules = [SplashFragmentBuilder::class])
    fun contributeSplashFragment(): SplashFragment

}