package ru.vitalysizov.moviedb.model.network.responses.people

import com.google.gson.annotations.SerializedName

data class PersonExternalIdsResponse(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("twitter_id")
    val twitterId: String?,

    @SerializedName("facebook_id")
    val faceBookId: String?,

    @SerializedName("tvrage_id")
    val tvRageId: Int?,

    @SerializedName("instagram_id")
    val instagramId: String?,

    @SerializedName("freebase_mid")
    val freebaseMid: String?,

    @SerializedName("imdb_id")
    val imDbId: String?,

    @SerializedName("freebase_id")
    val freebaseId: String?
)
