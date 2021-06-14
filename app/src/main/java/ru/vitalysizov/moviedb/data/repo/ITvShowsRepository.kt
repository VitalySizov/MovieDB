package ru.vitalysizov.moviedb.data.repo

import io.reactivex.Single
import ru.vitalysizov.moviedb.model.network.responses.tvShows.TvShowDetailsItemResponse

interface ITvShowsRepository {

    fun getTvShowById(tvShowId: Int): Single<TvShowDetailsItemResponse>

}