package ru.vitalysizov.moviedb.model.domain.tvShows

import ru.vitalysizov.moviedb.model.domain.base.BaseImages
import ru.vitalysizov.moviedb.model.domain.images.ImageItem

data class TvShowImages(
    override val id: Int,
    override val backdrops: List<ImageItem>,
    override val posters: List<ImageItem>
) : BaseImages