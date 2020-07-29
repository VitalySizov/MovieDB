package ru.vitalysizov.moviedb.domain.mapper.persons

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.model.domain.persons.PersonItem
import ru.vitalysizov.moviedb.model.network.responses.persons.PersonItemResponse
import javax.inject.Inject

class PersonsMapper @Inject constructor() : Mapper<PersonItemResponse, PersonItem> {
    override fun map(from: PersonItemResponse): PersonItem {
        return PersonItem(
            popularity = from.popularity ?: 0.0,
            knownForDepartment = from.knownForDepartment.orEmpty(),
            name = from.name.orEmpty(),
            profilePath = from.profilePath.orEmpty(),
            id = from.id ?: -1,
            adult = from.adult ?: false,
            gender = from.gender ?: -1
        )
    }
}