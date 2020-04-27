package ru.vitalysizov.moviedb.data.repo

import io.reactivex.Single
import ru.vitalysizov.moviedb.model.network.responses.base.BaseResponse
import ru.vitalysizov.moviedb.model.network.responses.genres.GenresResponse
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieResponse

interface IMoviesRepository {

    fun loadNowPlayingMovies(): Single<BaseResponse<MovieResponse>>

    fun loadPopularMovies(): Single<BaseResponse<MovieResponse>>

    fun loadMoviesGenres(): Single<GenresResponse>
}