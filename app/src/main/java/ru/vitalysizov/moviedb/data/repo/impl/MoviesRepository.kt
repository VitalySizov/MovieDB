package ru.vitalysizov.moviedb.data.repo.impl

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.remote.network.api.IMovieDbApiService
import ru.vitalysizov.moviedb.data.repo.IMoviesRepository
import ru.vitalysizov.moviedb.model.network.responses.base.BaseResponse
import ru.vitalysizov.moviedb.model.network.responses.genres.GenresResponse
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieResponse

class MoviesRepository(
    private val apiService: IMovieDbApiService
) : IMoviesRepository {

    override fun loadNowPlayingMovies(): Single<BaseResponse<MovieResponse>> {
        return apiService.loadNowPlayingMovies()
    }

    override fun loadPopularMovies(): Single<BaseResponse<MovieResponse>> {
        return apiService.loadPopularMovies()
    }

    override fun loadMoviesGenres(): Single<GenresResponse> {
        return apiService.loadMoviesGenres()
    }
}