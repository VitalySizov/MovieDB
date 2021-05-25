package ru.vitalysizov.moviedb.presentation.rated

import androidx.lifecycle.*
import io.reactivex.Single
import io.reactivex.functions.Function3
import ru.vitalysizov.moviedb.data.local.prefs.AccountPreferences
import ru.vitalysizov.moviedb.domain.params.account.RatedParams
import ru.vitalysizov.moviedb.domain.useCase.account.GetRatedMoviesUseCase
import ru.vitalysizov.moviedb.domain.useCase.account.GetRatedTvEpisodesUseCase
import ru.vitalysizov.moviedb.domain.useCase.account.GetRatedTvShowUseCase
import ru.vitalysizov.moviedb.model.domain.movies.RatedMovieItem
import ru.vitalysizov.moviedb.model.domain.tvEpisodes.RatedTvEpisodeItem
import ru.vitalysizov.moviedb.model.domain.tvShows.RatedTvShowItem
import ru.vitalysizov.moviedb.presentation.base.viewmodel.BaseViewModel
import ru.vitalysizov.moviedb.presentation.rated.content.RatedScreenContent
import ru.vitalysizov.moviedb.utils.ioToUi
import javax.inject.Inject

class RatedViewModel @Inject constructor(
    private val getRatedMoviesUseCase: GetRatedMoviesUseCase,
    private val getRatedTvShowUseCase: GetRatedTvShowUseCase,
    private val getRatedTvEpisodesUseCase: GetRatedTvEpisodesUseCase,
    private val accountPreferences: AccountPreferences
) : BaseViewModel(), LifecycleObserver {

    private val ratedContentMutable = MutableLiveData<RatedScreenContent>()
    val ratedContent: LiveData<RatedScreenContent> get() = ratedContentMutable

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onLifeCycleCreate() {
        loadRatedScreenContent()
    }

    private fun loadRatedScreenContent() {
        val params = RatedParams(
            accountId = accountPreferences.account?.id.toString(),
            sessionId = accountPreferences.sessionId.orEmpty()
        )

        launch {
            Single.zip(
                getRatedMoviesUseCase.invoke(params).ioToUi(),
                getRatedTvShowUseCase.invoke(params).ioToUi(),
                getRatedTvEpisodesUseCase.invoke(params).ioToUi(),
                Function3() { movies: List<RatedMovieItem>,
                              tvShows: List<RatedTvShowItem>,
                              tvEpisodes: List<RatedTvEpisodeItem> ->
                    return@Function3 RatedScreenContent(
                        movies, tvShows, tvEpisodes
                    )
                })
                .doOnSubscribe { showLoading() }
                .doAfterTerminate { hideLoading() }
                .subscribe({
                    ratedContentMutable.value = it
                }, {
                    handleError(it)
                })
        }
    }
}

