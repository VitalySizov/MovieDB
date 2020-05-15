package ru.vitalysizov.moviedb.model.network.responses.genres

import com.google.gson.annotations.SerializedName

data class GenreItemResponse(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?
)