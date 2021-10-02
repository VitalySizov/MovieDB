package ru.vitalysizov.moviedb.domain.mapper.movies.castAndCrew

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.domain.mapper.images.ImageTypes
import ru.vitalysizov.moviedb.domain.mapper.images.ImageUrlMapper
import ru.vitalysizov.moviedb.domain.mapper.images.UrlPathAndType
import ru.vitalysizov.moviedb.model.domain.castAndCrew.CrewItem
import ru.vitalysizov.moviedb.model.domain.enumerations.GenderType
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
                    gender = GenderType.fromIdentifier(
                        it.gender ?: GenderType.NOT_SPECIFIED.identifier
                    ),
                    id = it.id ?: 0,
                    job = it.job.orEmpty(),
                    name = it.name.orEmpty(),
                    profilePath = imageUrlMapper.map(
                        UrlPathAndType(
                            path = it.profilePath.orEmpty(),
                            imageType = ImageTypes.IMAGE_PROFILE
                        )
                    ),
                    adult = it.adult ?: false,
                    knownForDepartment = it.knownForDepartment.orEmpty(),
                    originalName = it.originalName.orEmpty(),
                    popularity = it.popularity ?: 0.0
                )
            }
        }
    }
}