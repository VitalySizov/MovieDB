package ru.vitalysizov.moviedb.data.repo

import io.reactivex.Single
import ru.vitalysizov.moviedb.model.network.responses.account.AccountDetailsResponse

interface IAccountRepository {

    fun getAccountDetails(sessionId: String): Single<AccountDetailsResponse>

}