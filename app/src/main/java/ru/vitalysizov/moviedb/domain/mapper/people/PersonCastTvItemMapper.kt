package ru.vitalysizov.moviedb.domain.mapper.people

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.model.domain.person.PersonCastTvItem
import ru.vitalysizov.moviedb.model.network.responses.people.PersonCastItemResponse
import javax.inject.Inject

class PersonCastTvItemMapper @Inject constructor() :
    Mapper<PersonCastItemResponse, PersonCastTvItem> {
    override fun map(from: PersonCastItemResponse): PersonCastTvItem {
        return PersonCastTvItem(
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
            episodeCount = from.episodeCount ?: -1,
            firstAirDate = from.firstAirDate.orEmpty(),
            name = from.name.orEmpty(),
            originalName = from.originalName.orEmpty(),
            originCountry = from.originCountry ?: emptyList()
        )
    }
}
