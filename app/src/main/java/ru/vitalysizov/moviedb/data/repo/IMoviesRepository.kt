package ru.vitalysizov.moviedb.data.repo

import io.reactivex.Single
import ru.vitalysizov.moviedb.model.network.responses.base.BaseResponse
import ru.vitalysizov.moviedb.model.network.responses.genres.GenresResponse
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieDetailsItemResponse
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieImagesResponse
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieResponse
import ru.vitalysizov.moviedb.model.network.responses.castAndCrew.CastAndCrewResponse

interface IMoviesRepository {

    fun loadNowPlayingMovies(): Single<BaseResponse<MovieResponse>>

    fun loadPopularMovies(): Single<BaseResponse<MovieResponse>>

    fun loadMoviesGenres(): Single<GenresResponse>

    fun loadMovieDetails(movieId: Int): Single<MovieDetailsItemResponse>

    fun loadMovieImages(movieId: Int): Single<MovieImagesResponse>

    fun loadCastAndCrew(movieId: Int): Single<CastAndCrewResponse>
}