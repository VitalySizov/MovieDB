package ru.vitalysizov.moviedb.model.domain.tvEpisodes

import org.threeten.bp.LocalDate

data class RatedTvEpisodeItem(
    val airDate: LocalDate,
    val episodeNumber: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val productionCode: String,
    val seasonNumber: Int,
    val show_id: Int,
    val stillPath: String,
    val voteAverage: Double,
    val voteCount: Int,
    val rating: Int
)