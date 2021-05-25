package ru.vitalysizov.moviedb.model.domain.tvShows

data class RatedTvShowItem(
    val tvShowItem: TvShowItem,
    val rating: Double
) : BaseTvShowItemData by tvShowItem