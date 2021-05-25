package ru.vitalysizov.moviedb.domain.useCase.authentication

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.IAuthenticationRepository
import ru.vitalysizov.moviedb.domain.params.authentication.CreateSessionParams
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.authentication.SessionItem
import javax.inject.Inject

class CreateSessionUseCase @Inject constructor(private val authenticationRepository: IAuthenticationRepository) :
    SingleWithParamsUseCase<CreateSessionParams, SessionItem>() {

    override fun invoke(params: CreateSessionParams): Single<SessionItem> {
        return authenticationRepository.createSession(params).map { item ->
            SessionItem(
                success = item.success ?: false,
                sessionId = item.sessionId.orEmpty()
            )
        }
    }
}