package ru.vitalysizov.moviedb.model.domain.tvShows

data class ContentRatingsItem(
    val results: List<ContentRatingItem>,
    val id: Int
)