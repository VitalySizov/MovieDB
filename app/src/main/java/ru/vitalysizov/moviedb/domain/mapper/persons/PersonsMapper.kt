package ru.vitalysizov.moviedb.domain.mapper.persons

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.domain.mapper.images.ImageTypes
import ru.vitalysizov.moviedb.domain.mapper.images.ImageUrlMapper
import ru.vitalysizov.moviedb.domain.mapper.images.UrlPathAndType
import ru.vitalysizov.moviedb.model.domain.enumerations.GenderType
import ru.vitalysizov.moviedb.model.domain.persons.PersonItem
import ru.vitalysizov.moviedb.model.network.responses.persons.PersonItemResponse
import javax.inject.Inject

class PersonsMapper @Inject constructor(
    private val imageUrlMapper: ImageUrlMapper
) : Mapper<PersonItemResponse, PersonItem> {
    override fun map(from: PersonItemResponse): PersonItem {
        return PersonItem(
            popularity = from.popularity ?: 0.0,
            knownForDepartment = from.knownForDepartment.orEmpty(),
            name = from.name.orEmpty(),
            profilePath = imageUrlMapper.map(
                UrlPathAndType(
                    path = from.profilePath.orEmpty(),
                    imageType = ImageTypes.IMAGE_PROFILE
                )
            ),
            id = from.id ?: -1,
            adult = from.adult ?: false,
            gender = GenderType.fromIdentifier(
                from.gender ?: GenderType.NOT_SPECIFIED.identifier
            )
        )
    }
}