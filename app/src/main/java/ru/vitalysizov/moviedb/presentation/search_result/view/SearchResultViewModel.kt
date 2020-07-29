package ru.vitalysizov.moviedb.presentation.search_result.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.functions.Function6
import ru.vitalysizov.moviedb.di.modules.screens.search_result.SearchResultModule
import ru.vitalysizov.moviedb.domain.params.search.DefaultSearchParams
import ru.vitalysizov.moviedb.domain.params.search.MoviesSearchParams
import ru.vitalysizov.moviedb.domain.params.search.PersonsSearchParams
import ru.vitalysizov.moviedb.domain.params.search.TvShowsSearchParams
import ru.vitalysizov.moviedb.domain.useCase.search.*
import ru.vitalysizov.moviedb.model.domain.base.BaseItem
import ru.vitalysizov.moviedb.model.domain.collection.CollectionItem
import ru.vitalysizov.moviedb.model.domain.companies.CompanyItem
import ru.vitalysizov.moviedb.model.domain.keywords.KeywordItem
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.model.domain.persons.PersonItem
import ru.vitalysizov.moviedb.model.domain.tvShows.TvShowItem
import ru.vitalysizov.moviedb.presentation.base.viewmodel.BaseViewModel
import ru.vitalysizov.moviedb.utils.Event
import ru.vitalysizov.moviedb.utils.ioToUi
import javax.inject.Inject
import javax.inject.Named

class SearchResultViewModel @Inject constructor(
    @Named(SearchResultModule.Params.QUERY) val query: String,
    private val searchMoviesByQueryUseCase: SearchMoviesByQueryUseCase,
    private val searchPersonsByQueryUseCase: SearchPersonsByQueryUseCase,
    private val searchCompaniesByQueryUseCase: SearchCompaniesByQueryUseCase,
    private val searchTvShowsByQueryUseCase: SearchTvShowsByQueryUseCase,
    private val searchCollectionByQueryUseCase: SearchCollectionByQueryUseCase,
    private val searchKeywordsByQueryUseCase: SearchKeywordsByQueryUseCase
) : BaseViewModel() {

    private val _searchResultContent = MutableLiveData<SearchResult>()
    val searchResultContent: LiveData<SearchResult>
        get() = _searchResultContent

    private val _movieDetailsClick = MutableLiveData<Event<Int>>()
    val movieDetailsClick: LiveData<Event<Int>>
        get() = _movieDetailsClick

    companion object {
        const val FIRST_FOUR_ELEMENTS = 4
    }

    init {
        searchByCurrentQuery()
    }

    private fun searchByCurrentQuery() {
        val moviesSearchParams = MoviesSearchParams(query = query, resultTake = FIRST_FOUR_ELEMENTS)
        val personsSearchParams =
            PersonsSearchParams(query = query, resultTake = FIRST_FOUR_ELEMENTS)
        val tvShowsSearchParams =
            TvShowsSearchParams(query = query, resultTake = FIRST_FOUR_ELEMENTS)
        val defaultSearchParams =
            DefaultSearchParams(query = query, resultTake = FIRST_FOUR_ELEMENTS)

        launch {
            showLoading()
            Single.zip(
                searchMoviesByQueryUseCase.invoke(moviesSearchParams).ioToUi(),
                searchPersonsByQueryUseCase.invoke(personsSearchParams).ioToUi(),
                searchCompaniesByQueryUseCase.invoke(defaultSearchParams).ioToUi(),
                searchTvShowsByQueryUseCase.invoke(tvShowsSearchParams).ioToUi(),
                searchCollectionByQueryUseCase.invoke(defaultSearchParams).ioToUi(),
                searchKeywordsByQueryUseCase.invoke(defaultSearchParams).ioToUi(),
                Function6() { movies: BaseItem<MovieItem>,
                              persons: BaseItem<PersonItem>,
                              companies: BaseItem<CompanyItem>,
                              tvShows: BaseItem<TvShowItem>,
                              collections: BaseItem<CollectionItem>,
                              keywords: BaseItem<KeywordItem> ->
                    return@Function6 SearchResult(
                        movies = movies,
                        persons = persons,
                        companies = companies,
                        tvShows = tvShows,
                        collections = collections,
                        keywords = keywords
                    )
                }
            ).subscribe({
                hideLoading()
                handleSuccessSearchDetails(it)
            }, this::handleError)
        }
    }

    private fun handleSuccessSearchDetails(items: SearchResult) {
        _searchResultContent.value = SearchResult(
            movies = items.movies,
            persons = items.persons,
            companies = items.companies,
            tvShows = items.tvShows,
            collections = items.collections,
            keywords = items.keywords
        )

    }

    fun setMovieDetailsClick(movieId: Int) {
        _movieDetailsClick.value = Event(movieId)
    }
}

data class SearchResult(
    val movies: BaseItem<MovieItem>,
    val persons: BaseItem<PersonItem>,
    val companies: BaseItem<CompanyItem>,
    val tvShows: BaseItem<TvShowItem>,
    val collections: BaseItem<CollectionItem>,
    val keywords: BaseItem<KeywordItem>
)