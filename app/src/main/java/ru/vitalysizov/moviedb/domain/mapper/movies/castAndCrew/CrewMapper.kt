package ru.vitalysizov.moviedb.domain.mapper.movies.castAndCrew

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.domain.mapper.images.ImageTypes
import ru.vitalysizov.moviedb.domain.mapper.images.ImageUrlMapper
import ru.vitalysizov.moviedb.domain.mapper.images.UrlPathAndType
import ru.vitalysizov.moviedb.model.domain.castAndCrew.CrewItem
import ru.vitalysizov.moviedb.model.network.responses.castAndCrew.CrewItemResponse
import javax.inject.Inject

class CrewMapper @Inject constructor(
    private val imageUrlMapper: ImageUrlMapper
) : Mapper<List<CrewItemResponse>?, List<CrewItem>> {

    override fun map(from: List<CrewItemResponse>?): List<CrewItem> {
        return if (from.isNullOrEmpty()) {
            emptyList()
        } else {
            from.map {
                CrewItem(
                    creditId = it.creditId.orEmpty(),
                    department = it.department.orEmpty(),
                    gender = it.gender ?: -1,
                    id = it.id ?: 0,
                    job = it.job.orEmpty(),
                    name = it.name.orEmpty(),
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