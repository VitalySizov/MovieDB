package ru.vitalysizov.moviedb.domain.mapper.movies

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.model.domain.movies.MovieImageItem
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieImageItemResponse
import javax.inject.Inject

class MovieImagesMapper @Inject constructor() :
    Mapper<List<MovieImageItemResponse>?, List<MovieImageItem>> {

    override fun map(from: List<MovieImageItemResponse>?): List<MovieImageItem> {
        if (from.isNullOrEmpty()) {
            return emptyList()
        }

        val list = arrayListOf<MovieImageItem>()
        from.forEach {
            list.add(
                MovieImageItem(
                    aspectRatio = it.aspectRatio ?: 0.0,
                    filePath = it.filePath.orEmpty(),
                    height = it.height ?: 0,
                    iso_639_1 = it.iso_639_1.orEmpty(),
                    voteAverage = it.voteAverage ?: 0.0,
                    voteCount = it.voteCount ?: 0,
                    width = it.width ?: 0
                )
            )
        }

        return list
    }
}
