package ru.vitalysizov.moviedb.domain.mapper.images

data class ImageQuality(
    val backdropSizes: Map<ImageQualityTypes, String>,
    val logoSizes: Map<ImageQualityTypes, String>,
    val posterSizes: Map<ImageQualityTypes, String>,
    val profileSizes: Map<ImageQualityTypes, String>,
    val stillSizes: Map<ImageQualityTypes, String>
)