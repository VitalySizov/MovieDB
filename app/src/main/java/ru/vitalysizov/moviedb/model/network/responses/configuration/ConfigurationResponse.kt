package ru.vitalysizov.moviedb.model.network.responses.configuration

import com.google.gson.annotations.SerializedName

data class ConfigurationResponse(

    @SerializedName("images")
    val images: ConfigurationImagesResponse?,

    @SerializedName("change_keys")
    val changeKeys: List<String>?
)