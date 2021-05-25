package ru.vitalysizov.moviedb.domain.params.base

enum class SortByCreatedType(val identifier: String) {
    CREATED_AT_ASC("created_at.asc"),
    CREATED_AT_DESC("created_at.desc")
}