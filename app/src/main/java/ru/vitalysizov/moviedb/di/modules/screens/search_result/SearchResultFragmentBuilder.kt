package ru.vitalysizov.moviedb.di.modules.screens.search_result

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.vitalysizov.moviedb.di.modules.ViewModelKey
import ru.vitalysizov.moviedb.presentation.search_result.view.SearchResultViewModel

@Module
interface SearchResultFragmentBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(SearchResultViewModel::class)
    fun bindSearchResultViewModel(viewModel: SearchResultViewModel): ViewModel
}