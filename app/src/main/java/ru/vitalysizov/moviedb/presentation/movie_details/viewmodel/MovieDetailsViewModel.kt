package ru.vitalysizov.moviedb.presentation.movie_details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xwray.groupie.Group
import io.reactivex.Single
import io.reactivex.functions.Function3
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.domain.useCase.castAndCrew.LoadCastAndCrewUseCase
import ru.vitalysizov.moviedb.domain.useCase.movies.details.LoadMovieDetailsUseCase
import ru.vitalysizov.moviedb.domain.useCase.movies.details.LoadMovieImagesUseCase
import ru.vitalysizov.moviedb.model.domain.movies.MovieDetailsItem
import ru.vitalysizov.moviedb.model.domain.movies.MovieImages
import ru.vitalysizov.moviedb.model.domain.castAndCrew.CastAndCrewItem
import ru.vitalysizov.moviedb.presentation.base.view.items.HeaderWithShowAllAdapterItem
import ru.vitalysizov.moviedb.presentation.base.view.items.SeparatorLineAdapterItem
import ru.vitalysizov.moviedb.presentation.base.view.items.SpaceAdapterItem
import ru.vitalysizov.moviedb.presentation.base.viewmodel.BaseViewModel
import ru.vitalysizov.moviedb.presentation.movie_details.view.items.*
import ru.vitalysizov.moviedb.utils.ioToUi
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val loadMovieDetailsUseCase: LoadMovieDetailsUseCase,
    private val loadMovieImagesUseCase: LoadMovieImagesUseCase,
    private val loadCastAndCrewUseCase: LoadCastAndCrewUseCase
) : BaseViewModel() {

    companion object {
        const val DEPARTMENT_DIRECTING = "Directing"
        const val DEPARTMENT_WRITING = "Writing"

        const val JOB_DIRECTOR = "Director"
    }

    private val uiItems = MutableLiveData<List<Group>>()
    val loadItems: LiveData<List<Group>> = uiItems

    val detailsResult = MutableLiveData<MovieDetailsItem>()

    fun loadMovieDetails(movieId: Int) {

        launch {
            Single.zip(loadMovieDetailsUseCase.invoke(movieId).ioToUi(),
                loadMovieImagesUseCase.invoke(movieId).ioToUi(),
                loadCastAndCrewUseCase.invoke(movieId).ioToUi(),

                Function3 { movieDetails: MovieDetailsItem,
                            movieImages: MovieImages,
                            castAndCrew: CastAndCrewItem ->

                    val backDropItems = CarouselBackDropAdapterItem(movieImages.backdrops.map { BackdropAdapterItem(it.filePath) })
                    val titleAdapterItem = TitleAdapterAdapterItem(movieDetails)
                    val genresAdapterItems: List<GenreAdapterItem> = movieDetails.genres.map { GenreAdapterItem(it, this::navigateToSearchByGenre) }
                    val posterAndDescriptionItem = PosterAndDescriptionAdapterItem(movieDetails, genresAdapterItems)
                    val headerAdapterItem = HeaderWithShowAllAdapterItem(R.string.cast_header, R.string.cast_all, this::navigateToShowAllCast)
                    val castAdapterItem = CarouselCastAdapterItem(castAndCrew.cast.map { CastAdapterItem(it, this::navigateToActorDetails) })

                    val directors = castAndCrew.crew.filter { it.department.contains(DEPARTMENT_DIRECTING).and(it.job == JOB_DIRECTOR) }
                    val writers = castAndCrew.crew.filter { it.department.contains(DEPARTMENT_WRITING) }

                    val directorsNames = directors.map { it.name }.joinToString { it }
                    val directorsIds = directors.map { it.id }
                    val writersNames = writers.map { it.name }.joinToString { it }
                    val writersIds = writers.map { it.id }

                    val crewDirectors = CrewAdapterItem(R.string.directors, directorsNames, directorsIds, this::navigateToCrewDetails)
                    val crewWriters = CrewAdapterItem(R.string.writers, writersNames, writersIds, this::navigateToCrewDetails)

                    val listItem = listOf(
                        backDropItems,
                        titleAdapterItem,
                        SeparatorLineAdapterItem(),
                        posterAndDescriptionItem,
                        SeparatorLineAdapterItem(),
                        headerAdapterItem,
                        castAdapterItem,
                        crewDirectors,
                        crewWriters,
                        SpaceAdapterItem()
                    )

                    uiItems.value = listItem

                }).subscribe({ }, { handleError(it) })
        }
    }

    private fun navigateToShowAllCast() {
        //TODO
    }

    private fun navigateToActorDetails(id: Int) {
        //TODO
    }

    private fun navigateToCrewDetails(ids: List<Int>) {
        //TODO
    }

    private fun navigateToSearchByGenre(id: Int) {
        //TODO
    }

}