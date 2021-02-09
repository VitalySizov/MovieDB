package ru.vitalysizov.moviedb.data.repo

import io.reactivex.Single
import ru.vitalysizov.moviedb.domain.params.authentication.CreateSessionParams
import ru.vitalysizov.moviedb.model.network.responses.authentication.RequestTokenResponse
import ru.vitalysizov.moviedb.model.network.responses.authentication.SessionResponse

interface IAuthenticationRepository {

    fun createRequestToken(): Single<RequestTokenResponse>

    fun createSession(createSessionParams: CreateSessionParams): Single<SessionResponse>
}