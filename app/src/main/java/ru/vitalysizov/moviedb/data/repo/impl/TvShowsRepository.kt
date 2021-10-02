package ru.vitalysizov.moviedb.data.repo.impl

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.remote.network.api.IMovieDbApiService
import ru.vitalysizov.moviedb.data.repo.ITvShowsRepository
import ru.vitalysizov.moviedb.model.network.responses.castAndCrew.CastAndCrewResponse
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieImagesResponse
import ru.vitalysizov.moviedb.model.network.responses.tvShows.ContentRatingsResponse
import ru.vitalysizov.moviedb.model.network.responses.tvShows.TvShowDetailsItemResponse

class TvShowsRepository(
    private val apiService: IMovieDbApiService
) : ITvShowsRepository {

    override fun getTvShowById(tvShowId: Int): Single<TvShowDetailsItemResponse> {
        return apiService.getTvShowDetailsById(tvShowId)
    }

    override fun getTvShowImages(tvShowId: Int): Single<MovieImagesResponse> {
        return apiService.getTvShowImages(tvShowId)
    }

    override fun getTvShowCredits(tvShowId: Int): Single<CastAndCrewResponse> {
        return apiService.getTvShowCredits(tvShowId)
    }

    override fun getTvShowContentRatings(tvShowId: Int): Single<ContentRatingsResponse> {
        return apiService.getTvShowContentRatings(tvShowId)
    }
}