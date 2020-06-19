package ru.vitalysizov.moviedb.model.domain.movies

data class MovieImages(
    val id: Int,
    val backdrops: List<MovieImageItem>,
    val posters: List<MovieImageItem>
)