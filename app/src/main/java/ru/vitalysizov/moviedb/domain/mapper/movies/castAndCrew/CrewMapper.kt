package ru.vitalysizov.moviedb.domain.mapper.movies.castAndCrew

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.model.domain.castAndCrew.CrewItem
import ru.vitalysizov.moviedb.model.network.responses.castAndCrew.CrewItemResponse
import javax.inject.Inject

class CrewMapper @Inject constructor() : Mapper<List<CrewItemResponse>?, List<CrewItem>> {

    override fun map(from: List<CrewItemResponse>?): List<CrewItem> {
        return if (from.isNullOrEmpty()) {
            emptyList()
        } else {
            from.map {
                CrewItem(
                    creditId = it.creditId.orEmpty(),
                    department = it.department.orEmpty(),
                    gender = it.gender ?: -1,
                    id = it.id ?: 0,
                    job = it.job.orEmpty(),
                    name = it.name.orEmpty(),
                    profilePath = it.profilePath.orEmpty()
                )
            }
        }
    }
}