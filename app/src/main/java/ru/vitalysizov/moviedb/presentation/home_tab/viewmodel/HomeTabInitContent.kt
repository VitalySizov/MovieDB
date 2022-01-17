package ru.vitalysizov.moviedb.presentation.home_tab.viewmodel

import ru.vitalysizov.moviedb.model.domain.movies.MovieItem

data class HomeTabInitContent(
    val nowPlayingMovies: List<MovieItem>,
    val popularMovies: List<MovieItem>,
    val topRatedMovies: List<MovieItem>
)