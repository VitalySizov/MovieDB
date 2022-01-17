package ru.vitalysizov.moviedb.data.repo.impl

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.remote.network.api.IMovieDbApiService
import ru.vitalysizov.moviedb.data.repo.IMoviesRepository
import ru.vitalysizov.moviedb.model.network.responses.base.BaseResponse
import ru.vitalysizov.moviedb.model.network.responses.castAndCrew.CastAndCrewResponse
import ru.vitalysizov.moviedb.model.network.responses.genres.GenresResponse
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieDetailsItemResponse
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieImagesResponse
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieItemResponse

class MoviesRepository(
    private val apiService: IMovieDbApiService
) : IMoviesRepository {

    override fun loadNowPlayingMovies(): Single<BaseResponse<MovieItemResponse>> {
        return apiService.loadNowPlayingMovies()
    }

    override fun loadPopularMovies(): Single<BaseResponse<MovieItemResponse>> {
        return apiService.loadPopularMovies()
    }

    override fun loadMoviesGenres(): Single<GenresResponse> {
        return apiService.loadMoviesGenres()
    }

    override fun loadMovieDetails(movieId: Int): Single<MovieDetailsItemResponse> {
        return apiService.loadMovieDetails(movieId)
    }

    override fun loadMovieImages(movieId: Int): Single<MovieImagesResponse> {
        return apiService.loadMovieImages(movieId)
    }

    override fun loadCastAndCrew(movieId: Int): Single<CastAndCrewResponse> {
        return apiService.loadCastAndCrew(movieId)
    }

    override fun getTopRatedMovies(): Single<BaseResponse<MovieItemResponse>> {
        return apiService.getTopRatedMovies()
    }
}