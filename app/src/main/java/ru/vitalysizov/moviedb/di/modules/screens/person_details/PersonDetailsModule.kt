package ru.vitalysizov.moviedb.di.modules.screens.person_details

import dagger.Module
import dagger.Provides
import ru.vitalysizov.moviedb.presentation.person_details.view.PersonDetailsFragment
import ru.vitalysizov.moviedb.presentation.person_details.view.PersonDetailsFragmentArgs
import javax.inject.Named

@Module
class PersonDetailsModule {

    object Params {
        const val PERSON_ID = "personId"
    }

    @Provides
    @Named(Params.PERSON_ID)
    fun providePersonId(fragment: PersonDetailsFragment): Int {
        return PersonDetailsFragmentArgs.fromBundle(fragment.requireArguments()).peopleId
    }
}