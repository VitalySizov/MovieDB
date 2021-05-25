package ru.vitalysizov.moviedb.domain.useCase.authentication

import io.reactivex.Single
import ru.vitalysizov.moviedb.BuildConfig
import ru.vitalysizov.moviedb.data.repo.IAuthenticationRepository
import ru.vitalysizov.moviedb.domain.useCase.base.BaseSingleUseCase
import ru.vitalysizov.moviedb.model.domain.authentication.RequestTokenItem
import javax.inject.Inject

class CreateRequestTokenUseCase @Inject constructor(
    private val authenticationRepository: IAuthenticationRepository
) : BaseSingleUseCase<Unit, RequestTokenItem>() {

    override fun invoke(): Single<RequestTokenItem> {
        return authenticationRepository.createRequestToken().map { item ->
            RequestTokenItem(
                success = item.success ?: false,
                expiresAt = item.expiresAt.orEmpty(),
                requestToken = item.requestToken.orEmpty()
            )
        }
    }

    override fun invoke(params: Unit): Single<RequestTokenItem> = invoke()

}