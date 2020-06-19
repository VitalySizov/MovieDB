package ru.vitalysizov.moviedb.model.domain.movies

data class MovieImageItem(
    val aspectRatio: Double,
    val filePath: String,
    val height: Int,
    val iso_639_1: String,
    val voteAverage: Double,
    val voteCount: Int,
    val width: Int
)