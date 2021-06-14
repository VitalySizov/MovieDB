package ru.vitalysizov.moviedb.model.network.responses.tvShows

import com.google.gson.annotations.SerializedName

data class CreatedByItemResponse(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("credit_id")
    val creditId: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("gender")
    val gender: Int?,

    @SerializedName("profile_path")
    val profilePath: String?
)