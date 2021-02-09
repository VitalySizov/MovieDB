package ru.vitalysizov.moviedb.data.repo.impl

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.remote.network.api.IMovieDbApiService
import ru.vitalysizov.moviedb.data.repo.IAuthenticationRepository
import ru.vitalysizov.moviedb.domain.params.authentication.CreateSessionParams
import ru.vitalysizov.moviedb.model.network.responses.authentication.RequestTokenResponse
import ru.vitalysizov.moviedb.model.network.responses.authentication.SessionResponse

class AuthenticationRepository(
    private val apiService: IMovieDbApiService
) : IAuthenticationRepository {

    override fun createRequestToken(): Single<RequestTokenResponse> {
        return apiService.createRequestToken()
    }

    override fun createSession(createSessionParams: CreateSessionParams): Single<SessionResponse> {
        return apiService.createSession(createSessionParams)
    }

}