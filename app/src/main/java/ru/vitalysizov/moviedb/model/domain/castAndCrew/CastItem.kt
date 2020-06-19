package ru.vitalysizov.moviedb.model.domain.castAndCrew

data class CastItem(
    val castId: Int,
    val character: String,
    val creditId: String,
    val gender: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val profilePath: String
)