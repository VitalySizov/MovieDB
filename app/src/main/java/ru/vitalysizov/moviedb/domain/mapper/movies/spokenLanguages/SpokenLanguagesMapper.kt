package ru.vitalysizov.moviedb.domain.mapper.movies.spokenLanguages

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.model.domain.spokenLanguages.SpokenLanguagesItem
import ru.vitalysizov.moviedb.model.network.responses.spokenLanguages.SpokenLanguagesItemResponse
import javax.inject.Inject

class SpokenLanguagesMapper @Inject constructor() :
    Mapper<List<SpokenLanguagesItemResponse>?, List<SpokenLanguagesItem>> {

    override fun map(from: List<SpokenLanguagesItemResponse>?): List<SpokenLanguagesItem> {
        return if (from.isNullOrEmpty()) {
            emptyList()
        } else {
            from.map {
                SpokenLanguagesItem(iso639_1 = it.iso639_1.orEmpty(), name = it.name.orEmpty())
            }
        }
    }
}