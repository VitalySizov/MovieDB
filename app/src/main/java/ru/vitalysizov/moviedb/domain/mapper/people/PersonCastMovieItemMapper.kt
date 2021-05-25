package ru.vitalysizov.moviedb.domain.mapper.people

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.domain.mapper.images.ImageTypes
import ru.vitalysizov.moviedb.domain.mapper.images.ImageUrlMapper
import ru.vitalysizov.moviedb.domain.mapper.images.UrlPathAndType
import ru.vitalysizov.moviedb.model.domain.person.PersonCastMovieItem
import ru.vitalysizov.moviedb.model.network.responses.people.PersonCastItemResponse
import javax.inject.Inject

class PersonCastMovieItemMapper @Inject constructor(
    private val imageUrlMapper: ImageUrlMapper
) : Mapper<PersonCastItemResponse, PersonCastMovieItem> {

    override fun map(from: PersonCastItemResponse): PersonCastMovieItem {
        return PersonCastMovieItem(
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
            originalTitle = from.originalTitle.orEmpty(),
            video = from.video ?: false,
            releaseDate = from.releaseDate.orEmpty(),
            title = from.title.orEmpty(),
            adult = from.adult ?: false
        )
    }

}