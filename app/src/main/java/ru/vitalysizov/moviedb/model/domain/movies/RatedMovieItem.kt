package ru.vitalysizov.moviedb.model.domain.movies

class RatedMovieItem(
    val movieItem: MovieItem,
    val rating: Double = 0.0
) : BaseMovieItemData by movieItem