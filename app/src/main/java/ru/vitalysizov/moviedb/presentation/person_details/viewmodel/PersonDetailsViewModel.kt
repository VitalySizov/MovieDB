package ru.vitalysizov.moviedb.presentation.person_details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.functions.Function4
import ru.vitalysizov.moviedb.di.modules.screens.person_details.PersonDetailsModule
import ru.vitalysizov.moviedb.domain.params.person.PersonCombinedCreditsParams
import ru.vitalysizov.moviedb.domain.useCase.person.LoadExternalIdsForPerson
import ru.vitalysizov.moviedb.domain.useCase.person.LoadPersonCombinedCreditsUseCase
import ru.vitalysizov.moviedb.domain.useCase.person.LoadPersonDetailsByIdUseCase
import ru.vitalysizov.moviedb.domain.useCase.person.LoadPersonImagesByIdUseCase
import ru.vitalysizov.moviedb.model.domain.person.*
import ru.vitalysizov.moviedb.presentation.base.viewmodel.BaseViewModel
import ru.vitalysizov.moviedb.utils.Event
import ru.vitalysizov.moviedb.utils.ioToUi
import javax.inject.Inject
import javax.inject.Named

class PersonDetailsViewModel @Inject constructor(
    @Named(PersonDetailsModule.Params.PERSON_ID) private val personId: Int,
    private val loadPersonDetailsByIdUseCase: LoadPersonDetailsByIdUseCase,
    private val loadPersonCombinedCreditsUseCase: LoadPersonCombinedCreditsUseCase,
    private val loadExternalIdsForPerson: LoadExternalIdsForPerson,
    private val loadPersonImagesByIdUseCase: LoadPersonImagesByIdUseCase
) : BaseViewModel() {

    private val _personContent = MutableLiveData<PersonDetailsContent>()
    val personContent: LiveData<PersonDetailsContent>
        get() = _personContent

    private val _movieDetailsClick = MutableLiveData<Event<Int>>()
    val movieDetailsClicked: LiveData<Event<Int>>
        get() = _movieDetailsClick

    private val _tvShowDetailsClick = MutableLiveData<Event<Int>>()
    val tvShowDetailsClick: LiveData<Event<Int>>
            get() = _tvShowDetailsClick

    init {
        loadPersonDetails()
    }

    private fun loadPersonDetails() {
        val personCombinedCreditsParams =
            PersonCombinedCreditsParams(personId = personId, sortedByDescendingReleaseDate = true)

        launch {
            Single.zip(
                loadPersonDetailsByIdUseCase.invoke(personId).ioToUi(),
                loadPersonCombinedCreditsUseCase.invoke(personCombinedCreditsParams).ioToUi(),
                loadExternalIdsForPerson.invoke(personId).ioToUi(),
                loadPersonImagesByIdUseCase.invoke(personId).ioToUi(),
                Function4() { personDetails: PersonDetails,
                              personCombinedCredits: PersonCombinedCredits,
                              personExternalIds: PersonExternalIds,
                              personImages: PersonImages ->
                    return@Function4 PersonDetailsContent(
                        personDetails = personDetails,
                        personCombinedCredits = personCombinedCredits,
                        personExternalIds = personExternalIds,
                        personImages = personImages
                    )
                }
            ).subscribe(this::handleSuccessPersonDetails, this::handleError)
        }
    }

    private fun handleSuccessPersonDetails(personDetailsContent: PersonDetailsContent) {
        _personContent.value = personDetailsContent
    }

    fun setMovieDetailsClick(movieId: Int) {
        _movieDetailsClick.value = Event(movieId)
    }

    fun setTvShowDetailsClick(tvShowId: Int) {
        _tvShowDetailsClick.value = Event((tvShowId))
    }

}

data class PersonDetailsContent(
    val personDetails: PersonDetails,
    val personCombinedCredits: PersonCombinedCredits,
    val personExternalIds: PersonExternalIds,
    val personImages: PersonImages
)