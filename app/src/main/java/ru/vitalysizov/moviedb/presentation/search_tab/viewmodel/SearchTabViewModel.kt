package ru.vitalysizov.moviedb.presentation.search_tab.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import ru.vitalysizov.moviedb.domain.params.search.MultiSearchParams
import ru.vitalysizov.moviedb.domain.useCase.search.SearchMultiByQueryUseCase
import ru.vitalysizov.moviedb.model.domain.base.BaseItem
import ru.vitalysizov.moviedb.presentation.base.viewmodel.BaseViewModel
import ru.vitalysizov.moviedb.utils.Event
import ru.vitalysizov.moviedb.utils.ioToUi
import javax.inject.Inject

class SearchTabViewModel @Inject constructor(
    private val searchMultiByQueryUseCase: SearchMultiByQueryUseCase
) : BaseViewModel() {

    private val _currentQuery = MutableLiveData<String>()
    private val currentQuery: LiveData<String>
        get() = _currentQuery

    private val _searchResult = MutableLiveData<List<Any>>()
    val searchResult: LiveData<List<Any>>
        get() = _searchResult

    private val _isEmpty = MediatorLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    private val _movieDetailsClick = MutableLiveData<Event<Int>>()
    val movieDetailsClick: LiveData<Event<Int>>
        get() = _movieDetailsClick

    private val tvShowDetailsClickedMutable = MutableLiveData<Event<Int>>()
    val tvShowDetailsClicked: LiveData<Event<Int>> get() = tvShowDetailsClickedMutable

    private val _searchResultsDetailsClick = MutableLiveData<Event<String>>()
    val searchResultsDetailsClick: LiveData<Event<String>>
        get() = _searchResultsDetailsClick

    private val _personDetailsClick = MutableLiveData<Event<Int>>()
    val personDetailsClick: LiveData<Event<Int>>
        get() = _personDetailsClick



    fun onSearchQueryChanged(query: String?) {
        _currentQuery.value = query
        searchByCurrentQuery()
    }

    private fun searchByCurrentQuery() {
        val query = currentQuery.value.orEmpty()

        if (query.isEmpty()) {
            _searchResult.value = emptyList()
            _isEmpty.value = false
            return
        }

        val multiSearchParams = MultiSearchParams(query = query)

        launch {
            showLoading()
            searchMultiByQueryUseCase.invoke(multiSearchParams)
                .ioToUi()
                .subscribe({
                    hideLoading()
                    handleSuccessMultiSearchByQuery(it)
                }, { handleError(it) })
        }
    }

    fun setSearchResultsDetailsClick() {
        val query = currentQuery.value.orEmpty()
        if (query.isEmpty()) {
            return
        }
        _searchResultsDetailsClick.value = Event(query)
    }

    fun setMovieDetailsClick(id: Int) {
        _movieDetailsClick.value = Event(id)
    }

    fun onTvShowItemClicked(id: Int) {
        tvShowDetailsClickedMutable.value = Event(id)
    }

    private fun handleSuccessMultiSearchByQuery(items: BaseItem<Any>) {
        _searchResult.value = items.result
        _isEmpty.value = items.result.isEmpty()
    }

    fun setPersonDetailsClick(personId: Int) {
        _personDetailsClick.value = Event(personId)
    }

}