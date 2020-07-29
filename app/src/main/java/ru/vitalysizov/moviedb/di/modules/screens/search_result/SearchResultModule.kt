package ru.vitalysizov.moviedb.di.modules.screens.search_result

import dagger.Module
import dagger.Provides
import ru.vitalysizov.moviedb.di.modules.screens.search_result.SearchResultModule.Params.QUERY
import ru.vitalysizov.moviedb.presentation.search_result.viewmodel.SearchResultFragment
import ru.vitalysizov.moviedb.presentation.search_result.viewmodel.SearchResultFragmentArgs
import javax.inject.Named

@Module
class SearchResultModule {

    object Params {
        const val QUERY = "query"
    }

    @Provides
    @Named(QUERY)
    fun provideSearchQuery(fragment: SearchResultFragment): String {
        return SearchResultFragmentArgs.fromBundle(fragment.requireArguments()).query
    }
}