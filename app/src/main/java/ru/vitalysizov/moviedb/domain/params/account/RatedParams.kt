package ru.vitalysizov.moviedb.domain.params.account

import ru.vitalysizov.moviedb.domain.params.base.SortByCreatedType

data class RatedParams(
    val accountId: String,
    val sessionId: String,
    val sortByCreated: SortByCreatedType = SortByCreatedType.CREATED_AT_DESC,
    val page: Int = 1,
)