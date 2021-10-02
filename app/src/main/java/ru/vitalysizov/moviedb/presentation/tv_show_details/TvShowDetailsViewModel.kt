package ru.vitalysizov.moviedb.presentation.tv_show_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.functions.Function4
import ru.vitalysizov.moviedb.di.modules.screens.tv_show_details.TvShowDetailsModule
import ru.vitalysizov.moviedb.domain.useCase.tvShows.LoadTvShowContentRatingsUseCase
import ru.vitalysizov.moviedb.domain.useCase.tvShows.LoadTvShowCreditsUsCase
import ru.vitalysizov.moviedb.domain.useCase.tvShows.LoadTvShowDetailsUseCase
import ru.vitalysizov.moviedb.domain.useCase.tvShows.LoadTvShowImagesUseCase
import ru.vitalysizov.moviedb.model.domain.castAndCrew.CastAndCrewItem
import ru.vitalysizov.moviedb.model.domain.tvShows.ContentRatingsItem
import ru.vitalysizov.moviedb.model.domain.tvShows.TvShowDetailsItem
import ru.vitalysizov.moviedb.model.domain.tvShows.TvShowImages
import ru.vitalysizov.moviedb.presentation.base.viewmodel.BaseViewModel
import ru.vitalysizov.moviedb.utils.ioToUi
import javax.inject.Inject
import javax.inject.Named

class TvShowDetailsViewModel @Inject constructor(
    @Named(TvShowDetailsModule.Params.TV_SHOW_ID) private val tvShowId: Int,
    private val loadTvShowDetailsUseCase: LoadTvShowDetailsUseCase,
    private val loadTvShowImagesUseCase: LoadTvShowImagesUseCase,
    private val loadTvShowCreditsUseCase: LoadTvShowCreditsUsCase,
    private val loadTvShowContentRatingsUseCase: LoadTvShowContentRatingsUseCase
) : BaseViewModel() {

    private val tvShowDetailsScreenContentMutable = MutableLiveData<TvShowDetailsScreenContent>()
    val tvShowDetailsScreenContent: LiveData<TvShowDetailsScreenContent> get() = tvShowDetailsScreenContentMutable

    init {
        loadTvShowDetails()
    }

    private fun loadTvShowDetails() {
        launch {
            Single.zip(
                loadTvShowDetailsUseCase.invoke(tvShowId).ioToUi(),
                loadTvShowImagesUseCase.invoke(tvShowId).ioToUi(),
                loadTvShowCreditsUseCase.invoke(tvShowId).ioToUi(),
                loadTvShowContentRatingsUseCase.invoke(tvShowId).ioToUi(),
                Function4() { tvShowDetailsItem: TvShowDetailsItem,
                              tvShowImages: TvShowImages,
                              castAndCrewItem: CastAndCrewItem,
                              contentRatings: ContentRatingsItem ->
                    return@Function4 TvShowDetailsScreenContent(
                        tvShowDetailsItem = tvShowDetailsItem,
                        tvShowImages = tvShowImages,
                        castAndCrewItem = castAndCrewItem,
                        contentRatingsItem = contentRatings
                    )
                }).subscribe({ response ->
                tvShowDetailsScreenContentMutable.value = response
            }, { error ->
                error.printStackTrace()
            })
        }
    }
}

data class TvShowDetailsScreenContent(
    val tvShowDetailsItem: TvShowDetailsItem,
    val tvShowImages: TvShowImages,
    val castAndCrewItem: CastAndCrewItem,
    val contentRatingsItem: ContentRatingsItem
)