package ru.vitalysizov.moviedb.model.network.responses.movies

import com.google.gson.annotations.SerializedName

data class MovieItemResponse(

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("vote_count")
    val voteCount: Int?,

    @SerializedName("video")
    val video: Boolean?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("adult")
    val adult: Boolean?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("backdrop_path")
    val backDropPath: String?,

    @SerializedName("original_language")
    val originalLanguage: String?,

    @SerializedName("original_title")
    val originalTitle: String?,

    @SerializedName("genre_ids")
    val genreIds: List<Int>?,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("vote_average")
    val voteAverage: Double?

)