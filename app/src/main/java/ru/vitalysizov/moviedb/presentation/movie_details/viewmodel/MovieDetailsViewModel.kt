package ru.vitalysizov.moviedb.presentation.movie_details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.functions.Function3
import ru.vitalysizov.moviedb.di.modules.screens.movie_details.MovieDetailsModule
import ru.vitalysizov.moviedb.domain.useCase.castAndCrew.LoadCastAndCrewParams
import ru.vitalysizov.moviedb.domain.useCase.castAndCrew.LoadCastAndCrewUseCase
import ru.vitalysizov.moviedb.domain.useCase.movies.details.LoadMovieDetailsUseCase
import ru.vitalysizov.moviedb.domain.useCase.movies.details.LoadMovieImagesUseCase
import ru.vitalysizov.moviedb.model.domain.castAndCrew.CastAndCrewItem
import ru.vitalysizov.moviedb.model.domain.movies.MovieDetailsItem
import ru.vitalysizov.moviedb.model.domain.movies.MovieImages
import ru.vitalysizov.moviedb.presentation.base.viewmodel.BaseViewModel
import ru.vitalysizov.moviedb.utils.ioToUi
import javax.inject.Inject
import javax.inject.Named

class MovieDetailsViewModel @Inject constructor(
    @Named(MovieDetailsModule.Params.MOVIE_ID) private val movieId: Int,
    private val loadMovieDetailsUseCase: LoadMovieDetailsUseCase,
    private val loadMovieImagesUseCase: LoadMovieImagesUseCase,
    private val loadCastAndCrewUseCase: LoadCastAndCrewUseCase
) : BaseViewModel() {

    private val movieDetailsContentMutable = MutableLiveData<MovieDetailsContent>()
    val movieDetailsContent: LiveData<MovieDetailsContent> get() = movieDetailsContentMutable

    init {
        loadMovieDetails(movieId)
    }

    private fun loadMovieDetails(movieId: Int) {
        launch {
            Single.zip(loadMovieDetailsUseCase.invoke(movieId).ioToUi(),
                loadMovieImagesUseCase.invoke(movieId).ioToUi(),
                loadCastAndCrewUseCase.invoke(LoadCastAndCrewParams(movieId)).ioToUi(),
                Function3 { movieDetails: MovieDetailsItem,
                            movieImages: MovieImages,
                            castAndCrew: CastAndCrewItem ->
                    return@Function3 MovieDetailsContent(
                        movieDetails = movieDetails,
                        movieImages = movieImages,
                        castAndCrew = castAndCrew
                    )
                })
                .doOnSubscribe { showLoading() }
                .doAfterTerminate { hideLoading() }
                .subscribe({
                    movieDetailsContentMutable.value = it
                }) {
                    this.handleError(it)
                }
        }
    }
}

data class MovieDetailsContent(
    val movieDetails: MovieDetailsItem,
    val movieImages: MovieImages,
    val castAndCrew: CastAndCrewItem
)