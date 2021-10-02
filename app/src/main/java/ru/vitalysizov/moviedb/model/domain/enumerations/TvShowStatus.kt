package ru.vitalysizov.moviedb.model.domain.enumerations

enum class TvShowStatus(val identifier: String) {
    PILOT("Pilot"),
    IN_PRODUCTION("In Production"),
    RETURNING_SERIES("Returning Series"),
    CANCELED("Canceled"),
    ENDED("Ended");

    companion object {
        fun fromIdentifier(id: String): TvShowStatus {
            return values().find { it.identifier == id }
                ?: throw Exception("TvShowStatus not found")
        }
    }
}