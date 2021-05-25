package ru.vitalysizov.moviedb.model.domain.movies

import org.threeten.bp.LocalDate

data class MovieItem(
    override val popularity: Double,
    override val voteCount: Int,
    override val video: Boolean,
    override val posterPath: String,
    override val id: Int,
    override val adult: Boolean,
    override val overview: String,
    override val backDropPath: String,
    override val originalLanguage: String,
    override val originalTitle: String,
    override val genreIds: List<Int>,
    override val releaseDate: LocalDate,
    override val title: String,
    override val voteAverage: String
) : BaseMovieItemData