package ru.vitalysizov.moviedb.model.domain.movies

import ru.vitalysizov.moviedb.model.domain.base.BaseImages
import ru.vitalysizov.moviedb.model.domain.images.ImageItem

data class MovieImages(
    override val id: Int,
    override val backdrops: List<ImageItem>,
    override val posters: List<ImageItem>
) : BaseImages