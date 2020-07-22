package ru.vitalysizov.moviedb.di.modules.screens

import dagger.Module
import dagger.Provides
import ru.vitalysizov.moviedb.di.modules.screens.MovieDetailsModule.Params.MOVIE_ID
import ru.vitalysizov.moviedb.presentation.movie_details.view.MovieDetailsFragment
import ru.vitalysizov.moviedb.presentation.movie_details.view.MovieDetailsFragmentArgs
import javax.inject.Named

@Module
class MovieDetailsModule {

    object Params {
        const val MOVIE_ID = "movieId"
    }

    @Provides
    @Named(MOVIE_ID)
    fun provideMovieId(fragment: MovieDetailsFragment): Int {
        return MovieDetailsFragmentArgs.fromBundle(fragment.requireArguments()).movieId
    }
}