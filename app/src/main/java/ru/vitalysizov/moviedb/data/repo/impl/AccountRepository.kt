package ru.vitalysizov.moviedb.data.repo.impl

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.remote.network.api.IMovieDbApiService
import ru.vitalysizov.moviedb.data.repo.IAccountRepository
import ru.vitalysizov.moviedb.model.network.responses.account.AccountDetailsResponse

class AccountRepository(
    private val apiService: IMovieDbApiService
) : IAccountRepository {

    override fun getAccountDetails(sessionId: String): Single<AccountDetailsResponse> {
        return apiService.getAccountDetails(sessionId)
    }
}