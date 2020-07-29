package ru.vitalysizov.moviedb.model.network.responses.companies

import com.google.gson.annotations.SerializedName

data class CompanyItemResponse(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("logo_path")
    val logoPath: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("origin_country")
    val originCountry: String?
)