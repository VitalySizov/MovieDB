package ru.vitalysizov.moviedb.domain.params.search

data class MultiSearchParams(
    val query: String,
    val page: Int = 1,
    val includeAdult: Boolean = false,
    val region: String = ""
)