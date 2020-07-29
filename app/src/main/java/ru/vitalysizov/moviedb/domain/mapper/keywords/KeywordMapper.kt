package ru.vitalysizov.moviedb.domain.mapper.keywords

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.model.domain.keywords.KeywordItem
import ru.vitalysizov.moviedb.model.network.responses.keywords.KeywordItemResponse
import javax.inject.Inject

class KeywordMapper @Inject constructor() : Mapper<KeywordItemResponse, KeywordItem> {
    override fun map(from: KeywordItemResponse): KeywordItem {
        return KeywordItem(
            id = from.id ?: -1,
            name = from.name.orEmpty()
        )
    }
}