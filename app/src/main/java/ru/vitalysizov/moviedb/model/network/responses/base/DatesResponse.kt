package ru.vitalysizov.moviedb.model.network.responses.base

import com.google.gson.annotations.SerializedName

data class DatesResponse(

    @SerializedName("maximum")
    val maximum: String?,

    @SerializedName("minimum")
    val minimum: String?
)