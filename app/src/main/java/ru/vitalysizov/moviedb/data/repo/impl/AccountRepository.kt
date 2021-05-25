package ru.vitalysizov.moviedb.data.repo.impl

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.remote.network.api.IMovieDbApiService
import ru.vitalysizov.moviedb.data.repo.IAccountRepository
import ru.vitalysizov.moviedb.domain.params.account.RatedParams
import ru.vitalysizov.moviedb.model.network.responses.account.AccountDetailsResponse
import ru.vitalysizov.moviedb.model.network.responses.base.BaseResponse
import ru.vitalysizov.moviedb.model.network.responses.movies.RatedMovieItemResponse
import ru.vitalysizov.moviedb.model.network.responses.tvEpisodes.RatedTvEpisodeItemResponse
import ru.vitalysizov.moviedb.model.network.responses.tvShows.RatedTvShowItemResponse

class AccountRepository(
    private val apiService: IMovieDbApiService
) : IAccountRepository {

    override fun getAccountDetails(sessionId: String): Single<AccountDetailsResponse> {
        return apiService.getAccountDetails(sessionId)
    }

    override fun getRatedMovies(params: RatedParams): Single<BaseResponse<RatedMovieItemResponse>> {
        return apiService.getRatedMovies(
            accountId = params.accountId,
            sessionId = params.sessionId,
            sortBy = params.sortByCreated.identifier,
            page = params.page
        )
    }

    override fun getRatedTvShow(params: RatedParams): Single<BaseResponse<RatedTvShowItemResponse>> {
        return apiService.getRatedTvShow(
            accountId = params.accountId,
            sessionId = params.sessionId,
            sortBy = params.sortByCreated.identifier,
            page = params.page
        )
    }

    override fun getRatedTvEpisodes(params: RatedParams): Single<BaseResponse<RatedTvEpisodeItemResponse>> {
        return apiService.getRatedTvEpisodes(
            accountId = params.accountId,
            sessionId = params.sessionId,
            sortBy = params.sortByCreated.identifier,
            page = params.page
        )
    }
}