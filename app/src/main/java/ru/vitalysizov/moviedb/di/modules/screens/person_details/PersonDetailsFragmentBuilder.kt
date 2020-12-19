package ru.vitalysizov.moviedb.di.modules.screens.person_details

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.vitalysizov.moviedb.di.modules.ViewModelKey
import ru.vitalysizov.moviedb.presentation.person_details.viewmodel.PersonDetailsViewModel

@Module
interface PersonDetailsFragmentBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(PersonDetailsViewModel::class)
    fun bindPeopleDetailsViewModel(viewModel: PersonDetailsViewModel): ViewModel
}