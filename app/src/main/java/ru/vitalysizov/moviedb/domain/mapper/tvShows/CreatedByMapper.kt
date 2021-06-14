package ru.vitalysizov.moviedb.domain.mapper.tvShows

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.domain.mapper.images.ImageTypes
import ru.vitalysizov.moviedb.domain.mapper.images.ImageUrlMapper
import ru.vitalysizov.moviedb.domain.mapper.images.UrlPathAndType
import ru.vitalysizov.moviedb.model.domain.tvShows.CreatedByItem
import ru.vitalysizov.moviedb.model.network.responses.tvShows.CreatedByItemResponse
import javax.inject.Inject

class CreatedByMapper @Inject constructor(
    private val imageUrlMapper: ImageUrlMapper
) : Mapper<CreatedByItemResponse, CreatedByItem> {
    override fun map(from: CreatedByItemResponse): CreatedByItem {
        return CreatedByItem(
            id = from.id ?: -1,
            creditId = from.creditId.orEmpty(),
            name = from.name.orEmpty(),
            gender = from.gender ?: -1,
            profilePath = imageUrlMapper.map(
                UrlPathAndType(
                    path = from.profilePath.orEmpty(),
                    imageType = ImageTypes.IMAGE_PROFILE
                )
            )
        )
    }
}