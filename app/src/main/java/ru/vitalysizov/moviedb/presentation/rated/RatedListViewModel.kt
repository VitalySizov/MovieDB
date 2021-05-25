package ru.vitalysizov.moviedb.presentation.rated

import androidx.lifecycle.*
import ru.vitalysizov.moviedb.di.modules.screens.rated.RatedListModule
import ru.vitalysizov.moviedb.model.domain.movies.RatedMovieItem
import ru.vitalysizov.moviedb.model.domain.tvEpisodes.RatedTvEpisodeItem
import ru.vitalysizov.moviedb.model.domain.tvShows.RatedTvShowItem
import ru.vitalysizov.moviedb.presentation.base.viewmodel.BaseViewModel
import ru.vitalysizov.moviedb.presentation.rated.content.RatedScreenContent
import ru.vitalysizov.moviedb.presentation.rated.content.RatedTab
import javax.inject.Inject
import javax.inject.Named

class RatedListViewModel @Inject constructor(
    @Named(RatedListModule.Params.RATED_CONTENT) private val ratedScreenContent: RatedScreenContent,
    @Named(RatedListModule.Params.TAB_DESCRIPTION) private val tab: RatedTab
) : BaseViewModel(), LifecycleObserver {

    private val moviesMutable = MutableLiveData<List<RatedMovieItem>>()
    val movies: LiveData<List<RatedMovieItem>> get() = moviesMutable

    private val tvShowsMutable = MutableLiveData<List<RatedTvShowItem>>()
    val tvShows: LiveData<List<RatedTvShowItem>> get() = tvShowsMutable

    private val tvEpisodesMutable = MutableLiveData<List<RatedTvEpisodeItem>>()
    val tvEpisodes: LiveData<List<RatedTvEpisodeItem>> get() = tvEpisodesMutable

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onLifeCycleCreate() {
        setupTabData()
    }

    private fun setupTabData() {
        when (tab) {
            RatedTab.MOVIES -> {
                moviesMutable.value = ratedScreenContent.movies
            }
            RatedTab.TV_SHOWS -> {
                tvShowsMutable.value = ratedScreenContent.tvShows
            }
            RatedTab.TV_EPISODES -> {
                tvEpisodesMutable.value = ratedScreenContent.tvEpisodes
            }
        }
    }
}