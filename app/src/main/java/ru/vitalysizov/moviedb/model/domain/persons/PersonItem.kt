package ru.vitalysizov.moviedb.model.domain.persons

import ru.vitalysizov.moviedb.model.domain.enumerations.GenderType

data class PersonItem(
    val popularity: Double,
    val knownForDepartment: String,
    val name: String,
    val id: Int,
    val profilePath: String,
    val adult: Boolean,
    val gender: GenderType

    //TODO add know_for
)