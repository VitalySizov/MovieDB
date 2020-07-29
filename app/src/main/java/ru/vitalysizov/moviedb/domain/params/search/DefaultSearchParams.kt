package ru.vitalysizov.moviedb.domain.params.search

data class DefaultSearchParams(
    val query: String,
    val page: Int = 1,
    val resultTake: Int = 20
)