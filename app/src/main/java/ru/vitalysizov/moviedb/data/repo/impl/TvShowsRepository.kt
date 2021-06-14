package ru.vitalysizov.moviedb.data.repo.impl

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.remote.network.api.IMovieDbApiService
import ru.vitalysizov.moviedb.data.repo.ITvShowsRepository
import ru.vitalysizov.moviedb.model.network.responses.tvShows.TvShowDetailsItemResponse

class TvShowsRepository(
    private val apiService: IMovieDbApiService
) : ITvShowsRepository {

    override fun getTvShowById(tvShowId: Int): Single<TvShowDetailsItemResponse> {
        return apiService.getTvShowDetailsById(tvShowId)
    }

}