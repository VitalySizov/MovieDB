package ru.vitalysizov.moviedb.presentation.settings.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.vitalysizov.moviedb.data.local.prefs.AccountPreferences
import ru.vitalysizov.moviedb.domain.mapper.images.ImageQualityTypes
import ru.vitalysizov.moviedb.presentation.base.viewmodel.BaseViewModel
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    val accountPreferences: AccountPreferences,
) : BaseViewModel() {

    private val imageQualityMutable = MutableLiveData<ImageQualityTypes>()
    val imageQuality: LiveData<ImageQualityTypes> get() = imageQualityMutable

    init {
        imageQualityMutable.value = accountPreferences.imageQuality
    }

    fun onCheckedIncludeAdultContent(checked: Boolean) {
        val account = accountPreferences.account
        account?.includeAdult = checked
        accountPreferences.account = account
    }

    fun onImageQualityChanged(positionId: Int) {
        val type = ImageQualityTypes.getTypeById(positionId)
        imageQualityMutable.value = type
        accountPreferences.imageQuality = type
    }

}