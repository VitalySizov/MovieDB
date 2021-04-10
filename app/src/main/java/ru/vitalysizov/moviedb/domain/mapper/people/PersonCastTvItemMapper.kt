package ru.vitalysizov.moviedb.domain.mapper.people

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.domain.mapper.images.ImageTypes
import ru.vitalysizov.moviedb.domain.mapper.images.ImageUrlMapper
import ru.vitalysizov.moviedb.domain.mapper.images.UrlPathAndType
import ru.vitalysizov.moviedb.model.domain.person.PersonCastTvItem
import ru.vitalysizov.moviedb.model.network.responses.people.PersonCastItemResponse
import javax.inject.Inject

class PersonCastTvItemMapper @Inject constructor(
    private val imageUrlMapper: ImageUrlMapper
) : Mapper<PersonCastItemResponse, PersonCastTvItem> {
    override fun map(from: PersonCastItemResponse): PersonCastTvItem {
        return PersonCastTvItem(
            id = from.id ?: -1,
            originalLanguage = from.originalLanguage.orEmpty(),
            overview = from.overview.orEmpty(),
            genreIds = from.genreIds ?: emptyList(),
            mediaType = from.mediaType.orEmpty(),
            popularity = from.popularity ?: 0.0,
            posterPath = imageUrlMapper.map(
                UrlPathAndType(
                    path = from.posterPath.orEmpty(),
                    imageType = ImageTypes.IMAGE_POSTER
                )
            ),
            voteCount = from.voteCount ?: 0,
            voteAverage = from.voteAverage ?: 0.0,
            character = from.character.orEmpty(),
            backdropPath = imageUrlMapper.map(
                UrlPathAndType(
                    path = from.backdropPath.orEmpty(),
                    imageType = ImageTypes.IMAGE_BACKDROP
                )
            ),
            creditId = from.creditId.orEmpty(),
            episodeCount = from.episodeCount ?: -1,
            firstAirDate = from.firstAirDate.orEmpty(),
            name = from.name.orEmpty(),
            originalName = from.originalName.orEmpty(),
            originCountry = from.originCountry ?: emptyList()
        )
    }
}
