package ru.vitalysizov.moviedb.model.network.responses.people

import com.google.gson.annotations.SerializedName

data class PersonCrewItemResponse(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("department")
    val department: String?,

    @SerializedName("original_language")
    val originalLanguage: String?,

    @SerializedName("episode_count")
    val episodeCount: Int?,

    @SerializedName("job")
    val job: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("origin_country")
    val originCountry: List<String>?,

    @SerializedName("original_name")
    val originalName: String?,

    @SerializedName("vote_count")
    val voteCount: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("media_type")
    val mediaType: String?,

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("credit_id")
    val creditId: String?,

    @SerializedName("genre_ids")
    val genreIds: List<Int>?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("first_air_date")
    val firstAirDate: String?,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("original_title")
    val originalTitle: String?,

    @SerializedName("video")
    val video: Boolean?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("adult")
    val adult: Boolean?,

    @SerializedName("release_date")
    val releaseDate: String?

)