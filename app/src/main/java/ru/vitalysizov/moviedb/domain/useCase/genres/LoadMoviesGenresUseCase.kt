package ru.vitalysizov.moviedb.domain.useCase.genres

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.IMoviesRepository
import ru.vitalysizov.moviedb.domain.mapper.genres.GenresMapper
import ru.vitalysizov.moviedb.domain.useCase.base.BaseSingleUseCase
import ru.vitalysizov.moviedb.model.domain.GenreItem
import javax.inject.Inject

class LoadMoviesGenresUseCase @Inject constructor(
    private val moviesRepository: IMoviesRepository,
    private val genresMapper: GenresMapper
) : BaseSingleUseCase<Unit, List<GenreItem>>() {

    override fun invoke(): Single<List<GenreItem>> {
        return moviesRepository.loadMoviesGenres()
            .map { list -> list.genres?.map { genresMapper.map(it) } }
    }

    override fun invoke(params: Unit): Single<List<GenreItem>> = invoke()
}