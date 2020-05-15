package ru.vitalysizov.moviedb.domain.mapper.genres

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.model.domain.GenreItem
import ru.vitalysizov.moviedb.model.network.responses.genres.GenreItemResponse
import javax.inject.Inject

class GenresMapper @Inject constructor() : Mapper<GenreItemResponse, GenreItem> {
    override fun map(from: GenreItemResponse): GenreItem {
        return GenreItem(id = from.id ?: 0, name = from.name.orEmpty())
    }
}