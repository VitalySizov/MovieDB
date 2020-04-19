package ru.vitalysizov.moviedb.presentation.home_tab.mvp

import com.xwray.groupie.Group
import com.xwray.groupie.kotlinandroidextensions.Item
import moxy.InjectViewState
import ru.vitalysizov.moviedb.domain.useCase.movies.LoadNowPlayingMoviesUseCase
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.presentation.base.mvp.BasePresenter
import ru.vitalysizov.moviedb.presentation.home_tab.view.IHomeTabView
import ru.vitalysizov.moviedb.presentation.home_tab.view.items.CarouselAdapterItem
import ru.vitalysizov.moviedb.presentation.home_tab.view.items.HeaderMovieAdapterItem
import ru.vitalysizov.moviedb.presentation.home_tab.view.items.MovieAdapterItem
import ru.vitalysizov.moviedb.utils.ioToUi
import javax.inject.Inject

@InjectViewState
class HomeTabPresenter @Inject constructor(
    private val loadNowPlayingMoviesUseCase: LoadNowPlayingMoviesUseCase
) : BasePresenter<IHomeTabView>() {

    private var uiItems = arrayListOf<Group>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadNowPlayingMovies()
    }

    private fun loadNowPlayingMovies() {
        launch {
            loadNowPlayingMoviesUseCase.invoke()
                .ioToUi()
                .subscribe(this::handleSuccessLoadNowPlayingMovies, this::handleError)
        }
    }

    private fun handleSuccessLoadNowPlayingMovies(movies: List<MovieItem>) {
        uiItems.add(HeaderMovieAdapterItem("TEST"))
        uiItems.add(prepareMovies(movies))
        viewState.setItems(uiItems)
    }

    private fun prepareMovies(items: List<MovieItem>) : CarouselAdapterItem {
        val moviesItem = arrayListOf<Item>()
        items.forEach { item -> moviesItem.add(MovieAdapterItem(item)) }
        return CarouselAdapterItem(moviesItem)
    }
}