package ru.vitalysizov.moviedb.domain.mapper.people

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.model.domain.person.PersonCastMovieItem
import ru.vitalysizov.moviedb.model.network.responses.people.PersonCastItemResponse
import javax.inject.Inject

class PersonCastMovieItemMapper @Inject constructor() : Mapper<PersonCastItemResponse, PersonCastMovieItem> {

    override fun map(from: PersonCastItemResponse): PersonCastMovieItem {
        return PersonCastMovieItem(
            id = from.id ?: -1,
            originalLanguage = from.originalLanguage.orEmpty(),
            overview = from.overview.orEmpty(),
            genreIds = from.genreIds ?: emptyList(),
            mediaType = from.mediaType.orEmpty(),
            popularity = from.popularity ?: 0.0,
            posterPath = from.posterPath.orEmpty(),
            voteCount = from.voteCount ?: 0,
            voteAverage = from.voteAverage ?: 0.0,
            character = from.character.orEmpty(),
            backdropPath = from.backdropPath.orEmpty(),
            creditId = from.creditId.orEmpty(),
            originalTitle = from.originalTitle.orEmpty(),
            video = from.video ?: false,
            releaseDate = from.releaseDate.orEmpty(),
            title = from.title.orEmpty(),
            adult = from.adult ?: false
        )
    }

}