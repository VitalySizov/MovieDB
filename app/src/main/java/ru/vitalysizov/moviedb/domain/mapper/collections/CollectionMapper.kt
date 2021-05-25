package ru.vitalysizov.moviedb.domain.mapper.collections

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.domain.mapper.images.ImageTypes
import ru.vitalysizov.moviedb.domain.mapper.images.ImageUrlMapper
import ru.vitalysizov.moviedb.domain.mapper.images.UrlPathAndType
import ru.vitalysizov.moviedb.model.domain.collection.CollectionItem
import ru.vitalysizov.moviedb.model.network.responses.collections.CollectionItemResponse
import javax.inject.Inject

class CollectionMapper @Inject constructor(
    private val imageUrlMapper: ImageUrlMapper
) : Mapper<CollectionItemResponse, CollectionItem> {
    override fun map(from: CollectionItemResponse): CollectionItem {
        return CollectionItem(
            id = from.id ?: -1,
            backdropPath = imageUrlMapper.map(
                UrlPathAndType(
                    path = from.backdropPath.orEmpty(),
                    imageType = ImageTypes.IMAGE_BACKDROP
                )
            ),
            name = from.name.orEmpty(),
            posterPath = imageUrlMapper.map(
                UrlPathAndType(
                    path = from.posterPath.orEmpty(),
                    imageType = ImageTypes.IMAGE_POSTER
                )
            )
        )
    }
}