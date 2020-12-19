package ru.vitalysizov.moviedb.model.domain.person

import ru.vitalysizov.moviedb.model.domain.images.ImageItem

data class PersonImages(
    val id: Int,
    val imagesList: List<ImageItem>
)