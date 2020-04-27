package ru.vitalysizov.moviedb.presentation.home_tab.mvp

import com.blankj.utilcode.util.StringUtils
import com.xwray.groupie.Group
import com.xwray.groupie.kotlinandroidextensions.Item
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function4
import moxy.InjectViewState
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.domain.useCase.genres.LoadMoviesGenresUseCase
import ru.vitalysizov.moviedb.domain.useCase.movies.LoadNowPlayingMoviesUseCase
import ru.vitalysizov.moviedb.domain.useCase.movies.LoadPopularMoviesUseCase
import ru.vitalysizov.moviedb.domain.useCase.movies.LoadTrendingMoviesUseCase
import ru.vitalysizov.moviedb.model.domain.GenreItem
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.presentation.base.mvp.BasePresenter
import ru.vitalysizov.moviedb.presentation.base.view.items.HeaderAdapterItem
import ru.vitalysizov.moviedb.presentation.base.view.items.SpaceAdapterItem
import ru.vitalysizov.moviedb.presentation.home_tab.view.IHomeTabView
import ru.vitalysizov.moviedb.presentation.home_tab.view.items.*
import ru.vitalysizov.moviedb.presentation.home_tab.view.items.HeaderMoviesAdapterItem.Companion.CHANGE_TYPE
import ru.vitalysizov.moviedb.utils.ioToUi
import javax.inject.Inject

@InjectViewState
class HomeTabPresenter @Inject constructor(
    private val loadNowPlayingMoviesUseCase: LoadNowPlayingMoviesUseCase,
    private val loadPopularMoviesUseCase: LoadPopularMoviesUseCase,
    private val loadMoviesGenresUseCase: LoadMoviesGenresUseCase,
    private val loadTrendingMoviesUseCase: LoadTrendingMoviesUseCase
) : BasePresenter<IHomeTabView>() {

    private val inTheatersMoviesList = arrayListOf<MovieItem>()
    private val popularMoviesList = arrayListOf<MovieItem>()
    private val trendingMoviesByDay = arrayListOf<MovieItem>()
    private val trendingMoviesByWeek = arrayListOf<MovieItem>()

    private lateinit var headerMoviesAdapterItem: HeaderMoviesAdapterItem
    private lateinit var carouselMoviesAdapterItem: CarouselMoviesAdapterItem
    private lateinit var carouselGenresAdapterItem: CarouselGenresAdapterItem
    private lateinit var headerTrendingMoviesAdapterItem: HeaderMoviesAdapterItem
    private lateinit var carouselMoviesTrendingAdapterItem: CarouselMoviesTrendingAdapterItem

    companion object {
        const val TIME_PARAMS_WEEK = "week"
        const val TIME_PARAMS_DAY = "day"
    }

    private var uiItems = arrayListOf<Group>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadHomeTabContent()
    }

    private fun loadNowPlayingMovies(): Single<List<MovieItem>> {
        return loadNowPlayingMoviesUseCase.invoke().ioToUi()
    }

    private fun loadPopularMovies(): Single<List<MovieItem>> {
        return loadPopularMoviesUseCase.invoke().ioToUi()
    }

    private fun loadMoviesGenres(): Single<List<GenreItem>> {
        return loadMoviesGenresUseCase.invoke().ioToUi()
    }

    private fun loadTrendingMoviesByDay(): Single<List<MovieItem>> {
        return loadTrendingMoviesUseCase.invoke(TIME_PARAMS_WEEK).ioToUi()
    }

    private fun loadTrendingMoviesByWeek(): Single<List<MovieItem>> {
        return loadTrendingMoviesUseCase.invoke(TIME_PARAMS_DAY).ioToUi()
    }

    private fun loadHomeTabContent() {
        viewState.showLoading(true)
        launch {
            Single.zip(
                loadNowPlayingMovies(),
                loadPopularMovies(),
                loadTrendingMoviesByDay(),
                loadTrendingMoviesByWeek(),
                Function4() { nowPlayingMovies: List<MovieItem>,
                              popularMovies: List<MovieItem>,
                              trendingMoviesByDay: List<MovieItem>,
                              trendingMoviesByWeek: List<MovieItem> ->
                    prepareCarouselMoviesAdapterItem(nowPlayingMovies, popularMovies)
                    prepareCarouselTrendingAdapterItem(trendingMoviesByDay, trendingMoviesByWeek)
                }).zipWith(
                loadMoviesGenres(),
                BiFunction { _: Unit, items: List<GenreItem> ->
                    prepareCarouselGenreAdapterItem(items)
                })
                .subscribe({
                    viewState.showLoading(false)
                    handleSuccessLoadHomeTabContent()
                }, (this::handleError))
        }
    }

    private fun handleSuccessLoadHomeTabContent() {
        uiItems.apply {
            add(headerMoviesAdapterItem)
            add(carouselMoviesAdapterItem)
            add(HeaderAdapterItem())
            add(carouselGenresAdapterItem)
            add(headerTrendingMoviesAdapterItem)
            add(carouselMoviesTrendingAdapterItem)
            add(SpaceAdapterItem())
        }
        viewState.setItems(uiItems)
    }

    private fun prepareCarouselMoviesAdapterItem(
        nowPlayingMovies: List<MovieItem>,
        popularMovies: List<MovieItem>
    ) {
        headerMoviesAdapterItem = HeaderMoviesAdapterItem(
            headerTitle = StringUtils.getString(R.string.now_in_theaters_header),
            changeListAction = { changeMoviesList(it) },
            firstNameRadioButton = StringUtils.getString(R.string.now_in_theaters_header),
            secondNameRadioButton = StringUtils.getString(R.string.populars_header)
        )
        inTheatersMoviesList.clear()
        inTheatersMoviesList.addAll(nowPlayingMovies)
        popularMoviesList.clear()
        popularMoviesList.addAll(popularMovies)
        carouselMoviesAdapterItem = getCarouselMoviesAdapterItem(nowPlayingMovies)
    }

    private fun prepareCarouselTrendingAdapterItem(
        itemsByDay: List<MovieItem>,
        itemsByWeek: List<MovieItem>
    ) {
        headerTrendingMoviesAdapterItem = HeaderMoviesAdapterItem(
            headerTitle = StringUtils.getString(R.string.trending_header),
            changeListAction = { changeMoviesTrendingList(it) },
            firstNameRadioButton = StringUtils.getString(R.string.day),
            secondNameRadioButton = StringUtils.getString(R.string.week)
        )
        trendingMoviesByDay.clear()
        trendingMoviesByDay.addAll(itemsByDay)
        trendingMoviesByWeek.clear()
        trendingMoviesByWeek.addAll(itemsByWeek)
        carouselMoviesTrendingAdapterItem = getCarouselTrendingMoviesAdapterItem(itemsByDay)
    }

    private fun prepareCarouselGenreAdapterItem(genres: List<GenreItem>) {
        carouselGenresAdapterItem = prepareCarouselGenres(genres)
    }

    private fun getCarouselMoviesAdapterItem(items: List<MovieItem>): CarouselMoviesAdapterItem {
        return CarouselMoviesAdapterItem(getMoviesAdapterItems(items))
    }

    private fun getCarouselTrendingMoviesAdapterItem(items: List<MovieItem>): CarouselMoviesTrendingAdapterItem {
        return CarouselMoviesTrendingAdapterItem(getTrendingMoviesAdapterItems(items))
    }

    private fun changeMoviesList(checkedId: Int) {
        when (checkedId) {
            R.id.rb_first -> {
                headerMoviesAdapterItem.setHeaderType(HeaderMoviesAdapterItem.HeaderType.InTheaters)
                headerMoviesAdapterItem.notifyChanged(CHANGE_TYPE)
                carouselMoviesAdapterItem.updateList(getMoviesAdapterItems(inTheatersMoviesList))
                carouselMoviesAdapterItem.notifyChanged()
            }
            R.id.rb_second -> {
                headerMoviesAdapterItem.setHeaderType(HeaderMoviesAdapterItem.HeaderType.Populars)
                headerMoviesAdapterItem.notifyChanged(CHANGE_TYPE)
                carouselMoviesAdapterItem.updateList(getMoviesAdapterItems(popularMoviesList))
                carouselMoviesAdapterItem.notifyChanged()
            }
        }
    }

    private fun changeMoviesTrendingList(checkedId: Int) {
        when (checkedId) {
            R.id.rb_first -> {
                headerTrendingMoviesAdapterItem.notifyChanged(CHANGE_TYPE)
                carouselMoviesTrendingAdapterItem.updateList(
                    getTrendingMoviesAdapterItems(
                        trendingMoviesByDay
                    )
                )
                carouselMoviesTrendingAdapterItem.notifyChanged()
            }
            R.id.rb_second -> {
                headerTrendingMoviesAdapterItem.notifyChanged(CHANGE_TYPE)
                carouselMoviesTrendingAdapterItem.updateList(
                    getTrendingMoviesAdapterItems(
                        trendingMoviesByWeek
                    )
                )
                carouselMoviesTrendingAdapterItem.notifyChanged()
            }
        }
    }

    private fun prepareCarouselGenres(items: List<GenreItem>): CarouselGenresAdapterItem {
        val genreItems = arrayListOf<Item>()
        items.forEach { item -> genreItems.add(GenreAdapterItem(item.name)) }
        return CarouselGenresAdapterItem(genreItems)
    }

    private fun getMoviesAdapterItems(items: List<MovieItem>): ArrayList<Item> {
        val moviesItems = arrayListOf<Item>()
        items.forEach { item -> moviesItems.add(MovieAdapterItem(item)) }
        return moviesItems
    }

    private fun getTrendingMoviesAdapterItems(items: List<MovieItem>): ArrayList<Item> {
        val moviesItems = arrayListOf<Item>()
        items.forEach { item -> moviesItems.add(MovieTrendingAdapterItem(item)) }
        return moviesItems
    }
}