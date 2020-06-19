package ru.vitalysizov.moviedb.domain.mapper.movies.belongsToCollection

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.model.domain.belongsToCollection.BelongsToCollectionItem
import ru.vitalysizov.moviedb.model.network.responses.belongsToCollection.BelongsToCollectionItemResponse
import javax.inject.Inject

class BelongsToCollectionMapper @Inject constructor() :
    Mapper<BelongsToCollectionItemResponse?, BelongsToCollectionItem> {

    override fun map(from: BelongsToCollectionItemResponse?): BelongsToCollectionItem {
        return BelongsToCollectionItem(
            id = from?.id ?: 0,
            name = from?.name.orEmpty(),
            posterPath = from?.posterPath.orEmpty(),
            backdropPath = from?.backdropPath.orEmpty()
        )
    }
}