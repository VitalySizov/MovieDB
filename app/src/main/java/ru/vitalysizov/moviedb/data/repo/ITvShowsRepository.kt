package ru.vitalysizov.moviedb.data.repo

import io.reactivex.Single
import ru.vitalysizov.moviedb.model.network.responses.castAndCrew.CastAndCrewResponse
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieImagesResponse
import ru.vitalysizov.moviedb.model.network.responses.tvShows.ContentRatingsResponse
import ru.vitalysizov.moviedb.model.network.responses.tvShows.TvShowDetailsItemResponse

interface ITvShowsRepository {

    fun getTvShowById(tvShowId: Int): Single<TvShowDetailsItemResponse>

    fun getTvShowImages(tvShowId: Int): Single<MovieImagesResponse>

    fun getTvShowCredits(tvShowId: Int): Single<CastAndCrewResponse>

    fun getTvShowContentRatings(tvShowId: Int): Single<ContentRatingsResponse>

}