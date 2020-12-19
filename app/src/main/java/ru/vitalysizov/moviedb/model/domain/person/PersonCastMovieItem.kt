package ru.vitalysizov.moviedb.model.domain.person

data class PersonCastMovieItem(
    val id: Int,
    val originalLanguage: String,
    val overview: String,
    val genreIds: List<Int>,
    val mediaType: String,
    val posterPath: String,
    val voteAverage: Double,
    val originalTitle: String,
    val voteCount: Int,
    val character: String,
    val backdropPath: String,
    val popularity: Double,
    val creditId: String,
    val video: Boolean,
    val releaseDate: String,
    val title: String,
    val adult: Boolean
)