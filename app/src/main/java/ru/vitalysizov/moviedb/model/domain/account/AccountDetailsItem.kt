package ru.vitalysizov.moviedb.model.domain.account

data class AccountDetailsItem(
    val avatarResponse: AvatarItem,
    val id: Int,
    val iso_639_1: String,
    val iso_3166_1: String,
    val name: String,
    val includeAdult: Boolean,
    val userName: String,
    var avatarUrl: String
)