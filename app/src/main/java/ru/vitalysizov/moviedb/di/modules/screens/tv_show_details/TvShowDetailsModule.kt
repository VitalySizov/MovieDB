package ru.vitalysizov.moviedb.di.modules.screens.tv_show_details

import dagger.Module
import dagger.Provides
import ru.vitalysizov.moviedb.di.modules.screens.tv_show_details.TvShowDetailsModule.Params.TV_SHOW_ID
import ru.vitalysizov.moviedb.presentation.tv_show_details.TvShowDetailsFragment
import ru.vitalysizov.moviedb.presentation.tv_show_details.TvShowDetailsFragmentArgs
import javax.inject.Named

@Module
class TvShowDetailsModule {

    object Params {
        const val TV_SHOW_ID = "tvShowId"
    }

    @Provides
    @Named(TV_SHOW_ID)
    fun provideTvShowId(fragment: TvShowDetailsFragment): Int {
        return TvShowDetailsFragmentArgs.fromBundle(fragment.requireArguments()).tvShowId
    }

}