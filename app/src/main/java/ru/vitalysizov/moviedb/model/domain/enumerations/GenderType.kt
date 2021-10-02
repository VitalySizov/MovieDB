package ru.vitalysizov.moviedb.model.domain.enumerations

enum class GenderType(val identifier: Int) {
    NOT_SPECIFIED(0),
    FEMALE(1),
    MALE(2);

    companion object {
        fun fromIdentifier(id: Int): GenderType {
            return values().find { it.identifier == id } ?: throw Exception("GenderType not found")
        }
    }

}