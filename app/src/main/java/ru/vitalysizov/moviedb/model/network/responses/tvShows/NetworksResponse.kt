package ru.vitalysizov.moviedb.model.network.responses.tvShows

import com.google.gson.annotations.SerializedName

data class NetworksResponse(

    @SerializedName("name")
    val name: String?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("logo_path")
    val logoPath: String?,

    @SerializedName("origin_country")
    val originCountry: String?

)