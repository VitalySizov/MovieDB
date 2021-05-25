package ru.vitalysizov.moviedb.di.modules.screens.rated

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.vitalysizov.moviedb.di.modules.ViewModelKey
import ru.vitalysizov.moviedb.presentation.rated.RatedViewModel

@Module
interface RatedFragmentBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(RatedViewModel::class)
    fun bindRatedViewModel(viewModel: RatedViewModel): ViewModel
}