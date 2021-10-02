package ru.vitalysizov.moviedb.di.modules.screens.tv_show_details

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.vitalysizov.moviedb.di.modules.ViewModelKey
import ru.vitalysizov.moviedb.presentation.tv_show_details.TvShowDetailsViewModel

@Module
interface TvShowDetailsFragmentBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(TvShowDetailsViewModel::class)
    fun bindTvShowDetailsViewModel(viewModel: TvShowDetailsViewModel): ViewModel


}