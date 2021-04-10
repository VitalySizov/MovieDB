package ru.vitalysizov.moviedb.presentation.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.vitalysizov.moviedb.data.local.prefs.ConfigurationPreferences
import ru.vitalysizov.moviedb.data.repo.impl.ConfigurationRepository
import ru.vitalysizov.moviedb.domain.useCase.configuration.FormatImageQualityUseCase
import ru.vitalysizov.moviedb.presentation.base.viewmodel.BaseViewModel
import ru.vitalysizov.moviedb.utils.Event
import ru.vitalysizov.moviedb.utils.ioToUi
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val configurationRepository: ConfigurationRepository,
    private val configurationPreferences: ConfigurationPreferences,
    private val formatImageQualityUseCase: FormatImageQualityUseCase
) : BaseViewModel() {

    private val prepareConfigurationMutable = MutableLiveData<Event<Boolean>>()
    val prepareConfiguration: LiveData<Event<Boolean>> get() = prepareConfigurationMutable

    init {
        loadConfiguration()
    }

    private fun loadConfiguration() {
        launch {
            configurationRepository.getConfiguration()
                .flatMap {
                    configurationPreferences.configuration = it
                    formatImageQualityUseCase.invoke(it)
                }
                .ioToUi()
                .subscribe({ response ->
                    configurationPreferences.imageQuality = response
                    prepareConfigurationMutable.value = Event(true)
                }, { error ->
                    error.printStackTrace()
                })
        }
    }

}