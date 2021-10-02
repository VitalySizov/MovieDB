package ru.vitalysizov.moviedb.model.domain.castAndCrew

import ru.vitalysizov.moviedb.model.domain.enumerations.GenderType

data class CrewItem(
    val creditId: String,
    val department: String,
    val gender: GenderType,
    val id: Int,
    val job: String,
    val name: String,
    val profilePath: String,
    val adult: Boolean,
    val knownForDepartment: String,
    val originalName: String,
    val popularity: Double
)