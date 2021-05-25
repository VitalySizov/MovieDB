package ru.vitalysizov.moviedb.model.network.responses.account

import com.google.gson.annotations.SerializedName

data class AccountDetailsResponse(
    @SerializedName("avatar")
    val avatarResponse: AvatarResponse?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("iso_639_1")
    val iso_639_1: String?,

    @SerializedName("iso_3166_1")
    val iso_3166_1: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("include_adult")
    val includeAdult: Boolean?,

    @SerializedName("username")
    val userName: String?
)