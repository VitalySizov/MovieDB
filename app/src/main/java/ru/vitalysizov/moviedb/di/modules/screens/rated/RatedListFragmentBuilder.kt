package ru.vitalysizov.moviedb.di.modules.screens.rated

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.vitalysizov.moviedb.di.modules.ViewModelKey
import ru.vitalysizov.moviedb.presentation.rated.RatedListViewModel

@Module
interface RatedListFragmentBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(RatedListViewModel::class)
    fun bindRatedListViewModel(ratedListViewModel: RatedListViewModel): ViewModel


}