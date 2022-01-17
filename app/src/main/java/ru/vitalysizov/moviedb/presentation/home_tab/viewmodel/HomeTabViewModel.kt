package ru.vitalysizov.moviedb.presentation.home_tab.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.functions.Function3
import ru.vitalysizov.moviedb.domain.useCase.movies.LoadNowPlayingMoviesUseCase
import ru.vitalysizov.moviedb.domain.useCase.movies.LoadPopularMoviesUseCase
import ru.vitalysizov.moviedb.domain.useCase.movies.LoadTopRatedMoviesUseCase
import ru.vitalysizov.moviedb.domain.useCase.tvShows.LoadPopularTvShowsUseCase
import ru.vitalysizov.moviedb.domain.useCase.tvShows.LoadTopRatedTvShowsUseCase
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.model.domain.tvShows.TvShowItem
import ru.vitalysizov.moviedb.presentation.base.viewmodel.BaseViewModel
import ru.vitalysizov.moviedb.presentation.home_tab.MediaTypeCategory
import ru.vitalysizov.moviedb.presentation.home_tab.MediaTypeCategoryItems
import ru.vitalysizov.moviedb.utils.Event
import ru.vitalysizov.moviedb.utils.ioToUi
import javax.inject.Inject

class HomeTabViewModel @Inject constructor(
    private val loadNowPlayingMoviesUseCase: LoadNowPlayingMoviesUseCase,
    private val loadPopularMoviesUseCase: LoadPopularMoviesUseCase,
    private val loadPopularTvShowsUseCase: LoadPopularTvShowsUseCase,
    private val loadTopRatedMoviesUseCase: LoadTopRatedMoviesUseCase,
    private val loadTopRatedTvShowsUseCase: LoadTopRatedTvShowsUseCase
) : BaseViewModel() {

    private val nowPlayingMoviesMutable = MutableLiveData<List<MovieItem>>()
    val nowPlayingMovies: LiveData<List<MovieItem>> get() = nowPlayingMoviesMutable

    private val currentPopularCategoryMutable = MutableLiveData<MediaTypeCategory>()
    val currentMediaTypeCategory: LiveData<MediaTypeCategory> get() = currentPopularCategoryMutable

    private val popularCategoryItemsMutable = MutableLiveData<MediaTypeCategoryItems>()
    val mediaTypeCategoryItems: LiveData<MediaTypeCategoryItems> get() = popularCategoryItemsMutable

    private val currentTopRatedCategoryMutable = MutableLiveData<MediaTypeCategory>()
    val currentTopRatedCategory: LiveData<MediaTypeCategory> get() = currentTopRatedCategoryMutable

    private val topRatedCategoryItemsMutable = MutableLiveData<MediaTypeCategoryItems>()
    val topRatedCategoryItems: LiveData<MediaTypeCategoryItems> get() = topRatedCategoryItemsMutable

    private val navigateToMovieDetailsMutable = MutableLiveData<Event<MovieItem>>()
    val navigateToMovieDetails: LiveData<Event<MovieItem>> get() = navigateToMovieDetailsMutable

    private val navigateToTvShowDetailsMutable = MutableLiveData<Event<TvShowItem>>()
    val navigateToTvShowDetails: LiveData<Event<TvShowItem>> get() = navigateToTvShowDetailsMutable

    init {
        loadHomeTabInitContent()
        initPopularCategory()
        initTopRatedCategory()
    }

    private fun initPopularCategory() {
        currentPopularCategoryMutable.value = MediaTypeCategory.MOVIES
    }

    private fun initTopRatedCategory() {
        currentTopRatedCategoryMutable.value = MediaTypeCategory.MOVIES
    }

    private fun loadHomeTabInitContent() {
        launch {
            Single.zip(
                loadNowPlayingMoviesUseCase.invoke().ioToUi(),
                loadPopularMoviesUseCase.invoke().ioToUi(),
                loadTopRatedMoviesUseCase.invoke().ioToUi(),
                Function3() { nowPlayingMoviesResponse: List<MovieItem>,
                              popularMoviesResponse: List<MovieItem>,
                              topRatedMoviesResponse: List<MovieItem> ->
                    return@Function3 HomeTabInitContent(
                        nowPlayingMovies = nowPlayingMoviesResponse,
                        popularMovies = popularMoviesResponse,
                        topRatedMovies = topRatedMoviesResponse
                    )
                }
            )
                .doOnSubscribe { showLoading() }
                .doAfterTerminate { hideLoading() }
                .subscribe(this::handleSuccessLoadHomeTabInitContent, this::handleError)
        }
    }

    private fun handleSuccessLoadHomeTabInitContent(homeTabInitContent: HomeTabInitContent) {
        with(homeTabInitContent) {
            nowPlayingMoviesMutable.value = nowPlayingMovies
            popularCategoryItemsMutable.value = MediaTypeCategoryItems.Movies(popularMovies)
            topRatedCategoryItemsMutable.value = MediaTypeCategoryItems.Movies(topRatedMovies)
        }
    }

    fun onChangeCurrentPopularCategory(selectedIndex: Int) {
        currentPopularCategoryMutable.value = MediaTypeCategory.getCategory(selectedIndex)
        getItemsByCurrentPopularCategory()
    }

    fun onChangeCurrentTopRatedCategory(selectedIndex: Int) {
        currentTopRatedCategoryMutable.value = MediaTypeCategory.getCategory(selectedIndex)
        getItemsByCurrentTopRatedCategory()
    }

    private fun getItemsByCurrentPopularCategory() {
        when (currentMediaTypeCategory.value) {
            MediaTypeCategory.MOVIES -> {
                getPopularMovies()
            }
            MediaTypeCategory.TV -> {
                getPopularTvShows()
            }
        }
    }

    private fun getItemsByCurrentTopRatedCategory() {
        when (currentTopRatedCategory.value) {
            MediaTypeCategory.MOVIES -> {
                getTopRatedMovies()
            }
            MediaTypeCategory.TV -> {
                getTopRatedTvShows()
            }
        }
    }

    private fun getPopularMovies() {
        launch {
            loadPopularMoviesUseCase.invoke().ioToUi()
                .subscribe({
                    popularCategoryItemsMutable.value = MediaTypeCategoryItems.Movies(it)
                }, { error ->
                    handleError(error)
                })
        }
    }

    private fun getPopularTvShows() {
        launch {
            loadPopularTvShowsUseCase.invoke().ioToUi()
                .subscribe({
                    popularCategoryItemsMutable.value = MediaTypeCategoryItems.TvShow(it)
                }, { error ->
                    handleError(error)
                })
        }
    }

    private fun getTopRatedMovies() {
        launch {
            loadTopRatedMoviesUseCase.invoke().ioToUi()
                .subscribe({
                    topRatedCategoryItemsMutable.value = MediaTypeCategoryItems.Movies(it)
                }, { error ->
                    handleError(error)
                })
        }
    }

    private fun getTopRatedTvShows() {
        launch {
            loadTopRatedTvShowsUseCase.invoke().ioToUi()
                .subscribe({
                    topRatedCategoryItemsMutable.value = MediaTypeCategoryItems.TvShow(it)
                }, { error ->
                    handleError(error)
                })
        }
    }

    fun onMovieClicked(movie: MovieItem) {
        navigateToMovieDetailsMutable.value = Event(movie)
    }

    fun onTvShowClicked(tvShow: TvShowItem) {
        navigateToTvShowDetailsMutable.value = Event(tvShow)
    }

}