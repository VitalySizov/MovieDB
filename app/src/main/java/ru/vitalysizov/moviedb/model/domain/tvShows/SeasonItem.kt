package ru.vitalysizov.moviedb.model.domain.tvShows

import org.threeten.bp.LocalDate

data class SeasonItem(
    val airDate: LocalDate,
    val episodeCount: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val seasonNumber: Int
)