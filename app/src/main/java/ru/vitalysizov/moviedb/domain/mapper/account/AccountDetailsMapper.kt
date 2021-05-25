package ru.vitalysizov.moviedb.domain.mapper.account

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.model.domain.account.AccountDetailsItem
import ru.vitalysizov.moviedb.model.domain.account.AvatarItem
import ru.vitalysizov.moviedb.model.network.responses.account.AccountDetailsResponse
import javax.inject.Inject

class AccountDetailsMapper @Inject constructor() :
    Mapper<AccountDetailsResponse, AccountDetailsItem> {

    override fun map(from: AccountDetailsResponse): AccountDetailsItem {
        return AccountDetailsItem(
            avatarResponse = AvatarItem(
                from.avatarResponse?.graAvatarResponse?.hash.orEmpty()
            ),
            id = from.id ?: 0,
            iso_639_1 = from.iso_639_1.orEmpty(),
            iso_3166_1 = from.iso_3166_1.orEmpty(),
            name = from.name.orEmpty(),
            includeAdult = from.includeAdult ?: false,
            userName = from.userName.orEmpty(),
            avatarUrl = ""
        )
    }
}