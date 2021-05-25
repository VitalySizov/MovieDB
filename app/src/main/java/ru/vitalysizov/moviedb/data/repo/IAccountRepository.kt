package ru.vitalysizov.moviedb.data.repo

import io.reactivex.Single
import ru.vitalysizov.moviedb.domain.params.account.RatedParams
import ru.vitalysizov.moviedb.model.network.responses.account.AccountDetailsResponse
import ru.vitalysizov.moviedb.model.network.responses.base.BaseResponse
import ru.vitalysizov.moviedb.model.network.responses.movies.RatedMovieItemResponse
import ru.vitalysizov.moviedb.model.network.responses.tvEpisodes.RatedTvEpisodeItemResponse
import ru.vitalysizov.moviedb.model.network.responses.tvShows.RatedTvShowItemResponse

interface IAccountRepository {

    fun getAccountDetails(sessionId: String): Single<AccountDetailsResponse>

    fun getRatedMovies(params: RatedParams): Single<BaseResponse<RatedMovieItemResponse>>

    fun getRatedTvShow(params: RatedParams): Single<BaseResponse<RatedTvShowItemResponse>>

    fun getRatedTvEpisodes(params: RatedParams): Single<BaseResponse<RatedTvEpisodeItemResponse>>

}