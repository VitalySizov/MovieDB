package ru.vitalysizov.moviedb.model.network.responses.castAndCrew

import com.google.gson.annotations.SerializedName

data class CastAndCrewResponse(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("cast")
    val cast: List<CastItemResponse>?,

    @SerializedName("crew")
    val crew: List<CrewItemResponse>?
)