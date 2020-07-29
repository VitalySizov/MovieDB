package ru.vitalysizov.moviedb.model.network.responses.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(

    @SerializedName("page")
    val page: Int?,

    @SerializedName("results")
    val result: List<T>?,

    @SerializedName("total_results")
    val totalResults: Int?,

    @SerializedName("total_pages")
    val totalPages: Int?,

    @SerializedName("dates")
    val dates: DatesResponse?
)
