package ru.vitalysizov.moviedb.domain.mapper.images

import java.lang.IllegalArgumentException

enum class ImageQualityTypes(val id: Int) {
    LOW_QUALITY(0),
    MEDIUM_QUALITY(1),
    HIGH_QUALITY(2);

    companion object {
        fun getTypeById(id: Int): ImageQualityTypes {
            val type = values().find { it.id == id }
            if (type != null) {
                return type
            } else {
                throw IllegalArgumentException("ImageQualityType id: $id not found")
            }
        }
    }
}
