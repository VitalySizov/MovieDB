package ru.vitalysizov.moviedb.model.network.responses.castAndCrew

import com.google.gson.annotations.SerializedName

data class CastItemResponse(

    @SerializedName("cast_id")
    val castId: Int?,

    @SerializedName("character")
    val character: String?,

    @SerializedName("credit_id")
    val creditId: String?,

    @SerializedName("gender")
    val gender: Int?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("order")
    val order: Int?,

    @SerializedName("profile_path")
    val profilePath: String?,

    @SerializedName("adult")
    val adult: Boolean?,

    @SerializedName("known_for_department")
    val knownForDepartment: String?,

    @SerializedName("original_name")
    val originalName: String?,

    @SerializedName("popularity")
    val popularity: Double?

)