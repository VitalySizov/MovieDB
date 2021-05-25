package ru.vitalysizov.moviedb.domain.mapper.movies.belongsToCollection

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.domain.mapper.images.ImageTypes
import ru.vitalysizov.moviedb.domain.mapper.images.ImageUrlMapper
import ru.vitalysizov.moviedb.domain.mapper.images.UrlPathAndType
import ru.vitalysizov.moviedb.model.domain.belongsToCollection.BelongsToCollectionItem
import ru.vitalysizov.moviedb.model.network.responses.belongsToCollection.BelongsToCollectionItemResponse
import javax.inject.Inject

class BelongsToCollectionMapper @Inject constructor(
    private val imageUrlMapper: ImageUrlMapper
) : Mapper<BelongsToCollectionItemResponse?, BelongsToCollectionItem> {

    override fun map(from: BelongsToCollectionItemResponse?): BelongsToCollectionItem {
        return BelongsToCollectionItem(
            id = from?.id ?: 0,
            name = from?.name.orEmpty(),
            posterPath = imageUrlMapper.map(
                UrlPathAndType(
                    path = from?.posterPath.orEmpty(), imageType = ImageTypes.IMAGE_POSTER
                )
            ),
            backdropPath = imageUrlMapper.map(
                UrlPathAndType(
                    path = from?.backdropPath.orEmpty(),
                    imageType = ImageTypes.IMAGE_BACKDROP
                )
            )
        )
    }
}