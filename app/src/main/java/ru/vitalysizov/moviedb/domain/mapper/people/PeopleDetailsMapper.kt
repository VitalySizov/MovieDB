package ru.vitalysizov.moviedb.domain.mapper.people

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.domain.mapper.images.ImageTypes
import ru.vitalysizov.moviedb.domain.mapper.images.ImageUrlMapper
import ru.vitalysizov.moviedb.domain.mapper.images.UrlPathAndType
import ru.vitalysizov.moviedb.model.domain.person.PersonDetails
import ru.vitalysizov.moviedb.model.network.responses.people.PeopleDetailsResponse
import ru.vitalysizov.moviedb.utils.DateHelper
import javax.inject.Inject

class PeopleDetailsMapper @Inject constructor(
    private val imageUrlMapper: ImageUrlMapper
) : Mapper<PeopleDetailsResponse, PersonDetails> {
    override fun map(from: PeopleDetailsResponse): PersonDetails {
        return PersonDetails(
            biography = from.biography.orEmpty(),
            birthDay = DateHelper.getLocalDate(from.birthday),
            knowForDepartment = from.knowForDepartment.orEmpty(),
            deathDay = DateHelper.getLocalDate(from.deathDay),
            id = from.id ?: -1,
            name = from.name.orEmpty(),
            alsoKnownAs = from.alsoKnownAs ?: emptyList(),
            popularity = from.popularity ?: 0.0,
            gender = from.gender ?: 0,
            placeOfBirth = from.placeOfBirth.orEmpty(),
            profilePath = imageUrlMapper.map(
                UrlPathAndType(
                    path = from.profilePath.orEmpty(),
                    imageType = ImageTypes.IMAGE_PROFILE
                )
            ),
            adult = from.adult ?: false,
            imdbId = from.imdbId.orEmpty(),
            homePage = from.homePage.orEmpty()
        )
    }
}