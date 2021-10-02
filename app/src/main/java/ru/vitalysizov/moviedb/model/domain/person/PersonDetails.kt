package ru.vitalysizov.moviedb.model.domain.person

import org.threeten.bp.LocalDate
import ru.vitalysizov.moviedb.model.domain.enumerations.GenderType

data class PersonDetails(
    val birthDay: LocalDate,
    val knowForDepartment: String,
    val deathDay: LocalDate,
    val id: Int,
    val name: String,
    val alsoKnownAs: List<String>,
    val gender: GenderType,
    val biography: String,
    val popularity: Double,
    val placeOfBirth: String,
    val profilePath: String,
    val adult: Boolean,
    val imdbId: String,
    val homePage: String
)