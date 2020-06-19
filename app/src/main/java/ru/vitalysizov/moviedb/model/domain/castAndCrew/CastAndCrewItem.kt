package ru.vitalysizov.moviedb.model.domain.castAndCrew

data class CastAndCrewItem(
    val id: Int,
    val cast: List<CastItem>,
    val crew: List<CrewItem>
)