package ru.vitalysizov.moviedb.model.domain.person

data class PersonExternalIds(
    val id: Int,
    val twitterId: String,
    val faceBookId: String,
    val tvRageId: Int,
    val instagramId: String,
    val freebaseMid: String,
    val imDbId: String,
    val freebaseId: String
)