package ru.vitalysizov.moviedb.domain.mapper.movies.castAndCrew

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.domain.mapper.images.ImageTypes
import ru.vitalysizov.moviedb.domain.mapper.images.ImageUrlMapper
import ru.vitalysizov.moviedb.domain.mapper.images.UrlPathAndType
import ru.vitalysizov.moviedb.model.domain.castAndCrew.CastItem
import ru.vitalysizov.moviedb.model.network.responses.castAndCrew.CastItemResponse
import javax.inject.Inject

class CastMapper @Inject constructor(
    private val imageUrlMapper: ImageUrlMapper
) : Mapper<List<CastItemResponse>?, List<CastItem>> {

    override fun map(from: List<CastItemResponse>?): List<CastItem> {
        return if (from.isNullOrEmpty()) {
            emptyList()
        } else {
            from.map {
                CastItem(
                    castId = it.castId ?: 0,
                    character = it.character.orEmpty(),
                    creditId = it.creditId.orEmpty(),
                    gender = it.gender ?: -1,
                    id = it.id ?: 0,
                    name = it.name.orEmpty(),
                    order = it.order ?: 0,
                    profilePath = imageUrlMapper.map(
                        UrlPathAndType(
                            path = it.profilePath.orEmpty(),
                            imageType = ImageTypes.IMAGE_PROFILE
                        )
                    )
                )
            }
        }
    }
}