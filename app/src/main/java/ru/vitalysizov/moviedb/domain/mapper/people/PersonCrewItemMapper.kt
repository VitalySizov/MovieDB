package ru.vitalysizov.moviedb.domain.mapper.people

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.domain.mapper.images.ImageTypes
import ru.vitalysizov.moviedb.domain.mapper.images.ImageUrlMapper
import ru.vitalysizov.moviedb.domain.mapper.images.UrlPathAndType
import ru.vitalysizov.moviedb.model.domain.person.PersonCrewItem
import ru.vitalysizov.moviedb.model.network.responses.people.PersonCrewItemResponse
import javax.inject.Inject

class PersonCrewItemMapper @Inject constructor(
    private val imageUrlMapper: ImageUrlMapper
) : Mapper<PersonCrewItemResponse, PersonCrewItem> {
    override fun map(from: PersonCrewItemResponse): PersonCrewItem {
        return PersonCrewItem(
            id = from.id ?: -1,
            department = from.department.orEmpty(),
            originalLanguage = from.originalLanguage.orEmpty(),
            episodeCount = from.episodeCount ?: -1,
            job = from.job.orEmpty(),
            overview = from.overview.orEmpty(),
            originCountry = from.originCountry ?: emptyList(),
            originalName = from.originalName.orEmpty(),
            voteCount = from.voteCount ?: 0,
            name = from.name.orEmpty(),
            mediaType = from.mediaType.orEmpty(),
            popularity = from.popularity ?: 0.0,
            creditId = from.creditId.orEmpty(),
            genreIds = from.genreIds ?: emptyList(),
            backdropPath = imageUrlMapper.map(
                UrlPathAndType(
                    path = from.backdropPath.orEmpty(),
                    imageType = ImageTypes.IMAGE_BACKDROP
                )
            ),
            firstAirDate = from.firstAirDate.orEmpty(),
            voteAverage = from.voteAverage ?: 0.0,
            posterPath = imageUrlMapper.map(
                UrlPathAndType(
                    path = from.posterPath.orEmpty(),
                    imageType = ImageTypes.IMAGE_POSTER
                )
            ),
            originalTitle = from.originalTitle.orEmpty(),
            video = from.video ?: false,
            adult = from.adult ?: false,
            title = from.title.orEmpty(),
            releaseDate = from.releaseDate.orEmpty()
        )
    }
}