package ru.vitalysizov.moviedb.model.network.responses.persons

import com.google.gson.annotations.SerializedName

data class PersonItemResponse(

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("known_for_department")
    val knownForDepartment: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("profile_path")
    val profilePath: String?,

    @SerializedName("adult")
    val adult: Boolean?,

    @SerializedName("gender")
    val gender: Int?

    //TODO add know_for

)