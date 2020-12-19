package ru.vitalysizov.moviedb.model.domain.images

data class ImageItem(
    val aspectRatio: Double,
    val filePath: String,
    val height: Int,
    val iso_639_1: String,
    val voteAverage: Double,
    val voteCount: Int,
    val width: Int
)