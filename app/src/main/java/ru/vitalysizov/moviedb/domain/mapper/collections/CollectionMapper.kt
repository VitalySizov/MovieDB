package ru.vitalysizov.moviedb.domain.mapper.collections

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.model.domain.collection.CollectionItem
import ru.vitalysizov.moviedb.model.network.responses.collections.CollectionItemResponse
import javax.inject.Inject

class CollectionMapper @Inject constructor() : Mapper<CollectionItemResponse, CollectionItem> {
    override fun map(from: CollectionItemResponse): CollectionItem {
        return CollectionItem(
            id = from.id ?: -1,
            backdropPath = from.backdropPath.orEmpty(),
            name = from.name.orEmpty(),
            posterPath = from.posterPath.orEmpty()
        )
    }
}