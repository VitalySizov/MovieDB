package ru.vitalysizov.moviedb.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.vitalysizov.moviedb.di.modules.screens.MainFragmentBuilder
import ru.vitalysizov.moviedb.di.modules.screens.MoviesDetailsFragmentBuilder
import ru.vitalysizov.moviedb.presentation.main.view.MainFragment
import ru.vitalysizov.moviedb.presentation.movie_details.view.MovieDetailsFragment

@Module
interface FragmentBuilder {

    @ContributesAndroidInjector(modules = [MainFragmentBuilder::class])
    fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector(modules = [MoviesDetailsFragmentBuilder::class])
    fun contributeMovieDetailsFragment(): MovieDetailsFragment

}