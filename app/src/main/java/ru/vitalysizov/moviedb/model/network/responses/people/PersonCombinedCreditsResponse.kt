package ru.vitalysizov.moviedb.model.network.responses.people

import com.google.gson.annotations.SerializedName

data class PersonCombinedCreditsResponse(

    @SerializedName("cast")
    val cast: List<PersonCastItemResponse>?,

    @SerializedName("crew")
    val crew: List<PersonCrewItemResponse>?,

    @SerializedName("id")
    val id: Int?

)