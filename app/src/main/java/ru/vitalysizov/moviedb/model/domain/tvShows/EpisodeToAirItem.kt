package ru.vitalysizov.moviedb.model.domain.tvShows

import org.threeten.bp.LocalDate

data class EpisodeToAirItem(
    val airDate: LocalDate,
    val episodeNumber: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val productionCode: String,
    val seasonNumber: Int,
    val still_path: String,
    val voteAverage: String,
    val voteCount: Int
)