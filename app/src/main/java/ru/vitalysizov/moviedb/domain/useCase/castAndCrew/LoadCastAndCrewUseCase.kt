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
) : SingleWithParamsUseCase<Int, CastAndCrewItem>() {

    override fun invoke(params: Int): Single<CastAndCrewItem> {
        return moviesRepository.loadCastAndCrew(params)
            .map {
                CastAndCrewItem(
                    id = it.id ?: 0,
                    cast = castMapper.map(it.cast),
                    crew = crewMapper.map(it.crew)
                )
            }
    }
}