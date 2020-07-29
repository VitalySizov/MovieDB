package ru.vitalysizov.moviedb.model.domain.base

data class BaseItem<T>(
    val page: Int,
    val result: List<T>,
    val totalResults: Int,
    val totalPages: Int,
    val dates: DatesItem
)