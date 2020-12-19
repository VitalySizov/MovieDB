package ru.vitalysizov.moviedb.model.domain.person

data class PersonCastTvItem(
    val id: Int,
    val originalLanguage: String,
    val episodeCount: Int,
    val overview: String,
    val originCountry: List<String>,
    val originalName: String,
    val genreIds: List<Int>,
    val name: String,
    val mediaType: String,
    val posterPath: String,
    val firstAirDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val character: String,
    val backdropPath: String,
    val popularity: Double,
    val creditId: String
)