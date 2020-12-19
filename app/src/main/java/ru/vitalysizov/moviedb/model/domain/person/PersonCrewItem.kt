package ru.vitalysizov.moviedb.model.domain.person

data class PersonCrewItem(
    val id: Int,
    val department: String,
    val originalLanguage: String,
    val episodeCount: Int,
    val job: String,
    val overview: String,
    val originCountry: List<String>,
    val originalName: String,
    val voteCount: Int,
    val name: String,
    val mediaType: String,
    val popularity: Double,
    val creditId: String,
    val genreIds: List<Int>,
    val backdropPath: String,
    val firstAirDate: String,
    val voteAverage: Double,
    val posterPath: String,
    val originalTitle: String,
    val video: Boolean,
    val title: String,
    val adult: Boolean,
    val releaseDate: String
)