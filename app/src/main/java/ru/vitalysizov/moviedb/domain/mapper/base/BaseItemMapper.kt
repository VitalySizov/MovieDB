package ru.vitalysizov.moviedb.domain.mapper.base

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.model.domain.base.BaseItem
import ru.vitalysizov.moviedb.model.domain.base.DatesItem
import ru.vitalysizov.moviedb.model.network.responses.base.BaseResponse
import javax.inject.Inject

class BaseItemMapper<T> @Inject constructor() : Mapper<BaseResponse<T>, BaseItem<T>> {

    override fun map(from: BaseResponse<T>): BaseItem<T> {
        return BaseItem(
            page = from.page ?: 1,
            result = from.result ?: emptyList(),
            totalResults = from.totalResults ?: 0,
            totalPages = from.totalPages ?: 1,
            dates = DatesItem(
                maximum = from.dates?.maximum.orEmpty(),
                minimum = from.dates?.minimum.orEmpty()
            )
        )
    }

}