package ru.vitalysizov.moviedb.model.domain.castAndCrew

import ru.vitalysizov.moviedb.model.domain.enumerations.GenderType

data class CastItem(
    val castId: Int,
    val character: String,
    val creditId: String,
    val gender: GenderType,
    val id: Int,
    val name: String,
    val order: Int,
    val profilePath: String,
    val adult: Boolean,
    val knownForDepartment: String,
    val originalName: String,
    val popularity: Double
)