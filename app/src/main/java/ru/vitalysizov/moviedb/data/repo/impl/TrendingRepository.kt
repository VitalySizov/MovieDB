package ru.vitalysizov.moviedb.data.repo.impl

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.remote.network.api.IMovieDbApiService
import ru.vitalysizov.moviedb.data.repo.ITrendingRepository
import ru.vitalysizov.moviedb.model.network.responses.base.BaseResponse
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieResponse

class TrendingRepository(
    private val apiService: IMovieDbApiService
) : ITrendingRepository {

    override fun loadTrending(
        mediaType: String,
        timeWindow: String
    ): Single<BaseResponse<MovieResponse>> {
        return apiService.loadTrendingItems(mediaType = mediaType, timeWindow = timeWindow)
    }
}