package ru.vitalysizov.moviedb.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.vitalysizov.moviedb.di.modules.screens.main.MainFragmentBuilder
import ru.vitalysizov.moviedb.di.modules.screens.movie_details.MovieDetailsModule
import ru.vitalysizov.moviedb.di.modules.screens.movie_details.MoviesDetailsFragmentBuilder
import ru.vitalysizov.moviedb.di.modules.screens.person_details.PersonDetailsFragmentBuilder
import ru.vitalysizov.moviedb.di.modules.screens.person_details.PersonDetailsModule
import ru.vitalysizov.moviedb.di.modules.screens.search_result.SearchResultFragmentBuilder
import ru.vitalysizov.moviedb.di.modules.screens.search_result.SearchResultModule
import ru.vitalysizov.moviedb.presentation.main.view.MainFragment
import ru.vitalysizov.moviedb.presentation.movie_details.view.MovieDetailsFragment
import ru.vitalysizov.moviedb.presentation.person_details.view.PersonDetailsFragment
import ru.vitalysizov.moviedb.presentation.search_result.viewmodel.SearchResultFragment

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

}