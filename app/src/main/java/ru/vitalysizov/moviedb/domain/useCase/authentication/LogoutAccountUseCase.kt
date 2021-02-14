package ru.vitalysizov.moviedb.domain.useCase.authentication

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.IAuthenticationRepository
import ru.vitalysizov.moviedb.domain.params.authentication.DeleteSessionParams
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.authentication.LogoutItem
import javax.inject.Inject

class LogoutAccountUseCase @Inject constructor(
    private val authenticationRepository: IAuthenticationRepository
) : SingleWithParamsUseCase<DeleteSessionParams, LogoutItem>() {
    override fun invoke(params: DeleteSessionParams): Single<LogoutItem> {
        return authenticationRepository.logoutAccount(params).map {
            LogoutItem(
                success = it.success ?: false
            )
        }
    }
}