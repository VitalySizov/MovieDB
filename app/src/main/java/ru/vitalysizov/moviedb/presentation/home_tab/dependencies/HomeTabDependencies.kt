package ru.vitalysizov.moviedb.presentation.home_tab.dependencies

import ru.vitalysizov.moviedb.di.base.ComponentDependencies
import ru.vitalysizov.moviedb.domain.useCase.genres.LoadMoviesGenresUseCase
import ru.vitalysizov.moviedb.domain.useCase.movies.LoadNowPlayingMoviesUseCase
import ru.vitalysizov.moviedb.domain.useCase.movies.LoadPopularMoviesUseCase
import ru.vitalysizov.moviedb.domain.useCase.movies.LoadTrendingMoviesUseCase

interface HomeTabDependencies : ComponentDependencies {
    fun loadNowPlayingUseCase(): LoadNowPlayingMoviesUseCase
    fun loadPopularMoviesUseCase(): LoadPopularMoviesUseCase
    fun loadMoviesGenresUseCase(): LoadMoviesGenresUseCase
    fun loadTrendingMoviesUseCase(): LoadTrendingMoviesUseCase
}