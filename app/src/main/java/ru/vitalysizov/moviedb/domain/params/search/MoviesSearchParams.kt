package ru.vitalysizov.moviedb.domain.params.search

data class MoviesSearchParams(
    val query: String,
    val page: Int = 1,
    val includeAdult: Boolean = false,
    val region: String = "",
    val year: Int = 0,
    val primaryReleaseYear: Int = 0,
    val resultTake: Int = 20
)