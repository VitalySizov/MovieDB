package ru.vitalysizov.moviedb.presentation.home_tab.dependencies

import ru.vitalysizov.moviedb.data.repo.IMoviesRepository
import ru.vitalysizov.moviedb.di.base.ComponentDependencies
import ru.vitalysizov.moviedb.domain.useCase.movies.LoadNowPlayingMoviesUseCase

interface HomeTabDependencies : ComponentDependencies {

    fun moviesRepository(): IMoviesRepository
    fun loadNowPlayingUseCase(): LoadNowPlayingMoviesUseCase

}