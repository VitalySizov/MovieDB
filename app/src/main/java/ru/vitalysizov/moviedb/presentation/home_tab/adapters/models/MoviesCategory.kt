package ru.vitalysizov.moviedb.presentation.home_tab.adapters.models

import ru.vitalysizov.moviedb.model.domain.movies.MovieItem

data class MoviesCategory(
    val title: Int,
    val movies: List<MovieItem>
)