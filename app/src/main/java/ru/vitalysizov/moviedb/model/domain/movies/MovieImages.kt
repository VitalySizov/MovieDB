package ru.vitalysizov.moviedb.model.domain.movies

import ru.vitalysizov.moviedb.model.domain.images.ImageItem

data class MovieImages(
    val id: Int,
    val backdrops: List<ImageItem>,
    val posters: List<ImageItem>
)