package ru.vitalysizov.moviedb.domain.useCase.account

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.IAccountRepository
import ru.vitalysizov.moviedb.data.repo.impl.AccountRepository
import ru.vitalysizov.moviedb.domain.mapper.account.AccountDetailsMapper
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.account.AccountDetailsItem
import javax.inject.Inject

class GetAccountDetailsUseCase @Inject constructor(
    private val accountRepository: IAccountRepository,
    private val accountDetailsMapper: AccountDetailsMapper
) : SingleWithParamsUseCase<String, AccountDetailsItem>() {

    override fun invoke(params: String): Single<AccountDetailsItem> {
        return accountRepository.getAccountDetails(params).map { accountDetailsMapper.map(it) }
    }

}