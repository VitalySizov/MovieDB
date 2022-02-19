package ru.vitalysizov.moviedb.domain.useCase.castAndCrew

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.IMoviesRepository
import ru.vitalysizov.moviedb.domain.mapper.movies.castAndCrew.CastMapper
import ru.vitalysizov.moviedb.domain.mapper.movies.castAndCrew.CrewMapper
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.castAndCrew.CastAndCrewItem
import javax.inject.Inject

class LoadCastAndCrewUseCase @Inject constructor(
    private val moviesRepository: IMoviesRepository,
    private val castMapper: CastMapper,
    private val crewMapper: CrewMapper
) : SingleWithParamsUseCase<LoadCastAndCrewParams, CastAndCrewItem>() {

    override fun invoke(params: LoadCastAndCrewParams): Single<CastAndCrewItem> {
        val maxCountCastItems = params.maxCountCastItems
        return moviesRepository.loadCastAndCrew(params.movieId)
            .map {
                val cast = castMapper.map(it.cast)
                CastAndCrewItem(
                    id = it.id ?: 0,
                    cast = if (maxCountCastItems == null) cast else cast.take(maxCountCastItems),
                    crew = crewMapper.map(it.crew)
                )
            }
    }
}

data class LoadCastAndCrewParams(
    val movieId: Int,
    val maxCountCastItems: Int? = 10
)