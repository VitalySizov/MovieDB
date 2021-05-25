package ru.vitalysizov.moviedb.di.modules.screens.rated

import dagger.Module
import dagger.Provides
import ru.vitalysizov.moviedb.di.modules.screens.rated.RatedListModule.Params.RATED_CONTENT
import ru.vitalysizov.moviedb.di.modules.screens.rated.RatedListModule.Params.TAB_DESCRIPTION
import ru.vitalysizov.moviedb.presentation.rated.RatedListFragment
import ru.vitalysizov.moviedb.presentation.rated.RatedListFragmentArgs
import ru.vitalysizov.moviedb.presentation.rated.content.RatedScreenContent
import ru.vitalysizov.moviedb.presentation.rated.content.RatedTab
import javax.inject.Named

@Module
class RatedListModule {

    object Params {
        const val RATED_CONTENT = "content"
        const val TAB_DESCRIPTION = "description"
    }

    @Provides
    @Named(RATED_CONTENT)
    fun provideContentParams(fragment: RatedListFragment): RatedScreenContent {
        return RatedListFragmentArgs.fromBundle(fragment.requireArguments()).ratedContent
    }

    @Provides
    @Named(TAB_DESCRIPTION)
    fun provideTabPositionParam(fragment: RatedListFragment): RatedTab {
        return RatedListFragmentArgs.fromBundle(fragment.requireArguments()).tab
    }


}