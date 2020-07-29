package ru.vitalysizov.moviedb.domain.params.search

data class TvShowsSearchParams(
    val query: String,
    val page: Int = 1,
    val includeAdult: Boolean = false,
    val firstAirDateYear: Int = 0,
    val resultTake: Int = 20
)