package ru.vitalysizov.moviedb.model.domain.tvShows

import org.threeten.bp.LocalDate

data class TvShowItem(
    val posterPath: String,
    val popularity: Double,
    val id: Int,
    val backdropPath: String,
    val voteAverage: String,
    val overview: String,
    val firstAirDate: LocalDate,
    val originCountry: List<String>,
    val genreIds: List<Int>,
    val originalLanguage: String,
    val voteCount: Int,
    val name: String,
    val originalName: String
)