package ru.vitalysizov.moviedb.model.network.responses.castAndCrew

import com.google.gson.annotations.SerializedName

data class CrewItemResponse(

    @SerializedName("credit_id")
    val creditId: String?,

    @SerializedName("department")
    val department: String?,

    @SerializedName("gender")
    val gender: Int?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("job")
    val job: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("profile_path")
    val profilePath: String?

)