package ru.vitalysizov.moviedb.model.domain.configuration

data class ConfigurationItem(
    val images: ConfigurationImagesItem,
    val changeKeys: List<String>
)