package ru.vitalysizov.moviedb.model.domain.base

import ru.vitalysizov.moviedb.model.domain.images.ImageItem

interface BaseImages {
    val id: Int
    val backdrops: List<ImageItem>
    val posters: List<ImageItem>
}