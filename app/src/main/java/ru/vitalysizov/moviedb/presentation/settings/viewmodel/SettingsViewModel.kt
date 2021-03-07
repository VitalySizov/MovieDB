package ru.vitalysizov.moviedb.presentation.settings.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.vitalysizov.moviedb.data.local.prefs.AccountPreferences
import ru.vitalysizov.moviedb.model.domain.account.AccountDetailsItem
import ru.vitalysizov.moviedb.presentation.base.viewmodel.BaseViewModel
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val accountPreferences: AccountPreferences,
) : BaseViewModel() {

    private val accountDetailsMutable = MutableLiveData<AccountDetailsItem>()
    val accountDetails: LiveData<AccountDetailsItem>
        get() = accountDetailsMutable

    init {
        accountDetailsMutable.value = accountPreferences.account
    }
}