package ru.vitalysizov.moviedb.model.domain.tvShows

import org.threeten.bp.LocalDate

data class TvShowItem(
    override val posterPath: String,
    override val popularity: Double,
    override val id: Int,
    override val backdropPath: String,
    override val voteAverage: String,
    override val overview: String,
    override val firstAirDate: LocalDate,
    override val originCountry: List<String>,
    override val genreIds: List<Int>,
    override val originalLanguage: String,
    override val voteCount: Int,
    override val name: String,
    override val originalName: String
) : BaseTvShowItemData