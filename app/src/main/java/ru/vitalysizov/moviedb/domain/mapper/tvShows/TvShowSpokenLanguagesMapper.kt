package ru.vitalysizov.moviedb.domain.mapper.tvShows

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.model.domain.tvShows.TvShowSpokenLanguagesItem
import ru.vitalysizov.moviedb.model.network.responses.tvShows.TvShowSpokenLanguagesItemResponse
import javax.inject.Inject

class TvShowSpokenLanguagesMapper @Inject constructor() : Mapper<TvShowSpokenLanguagesItemResponse, TvShowSpokenLanguagesItem> {
    override fun map(from: TvShowSpokenLanguagesItemResponse): TvShowSpokenLanguagesItem {
        return TvShowSpokenLanguagesItem(
            englishName = from.englishName.orEmpty(),
            iso639_1 = from.iso639_1.orEmpty(),
            name = from.name.orEmpty()
        )
    }
}