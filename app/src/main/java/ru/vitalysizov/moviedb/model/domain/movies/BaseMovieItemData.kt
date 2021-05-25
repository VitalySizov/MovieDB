package ru.vitalysizov.moviedb.model.domain.movies

import org.threeten.bp.LocalDate

interface BaseMovieItemData {
    val popularity: Double
    val voteCount: Int
    val video: Boolean
    val posterPath: String
    val id: Int
    val adult: Boolean
    val overview: String
    val backDropPath: String
    val originalLanguage: String
    val originalTitle: String
    val genreIds: List<Int>
    val releaseDate: LocalDate
    val title: String
    val voteAverage: String
}