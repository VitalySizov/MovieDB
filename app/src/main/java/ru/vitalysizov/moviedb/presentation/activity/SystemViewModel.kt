package ru.vitalysizov.moviedb.presentation.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SystemViewModel @Inject constructor() : ViewModel() {

    private val authRequestTokenMutable: MutableLiveData<String> = MutableLiveData()
    val authRequestToken: LiveData<String> get() = authRequestTokenMutable

    fun setAuthRequestToken(requestToken: String) {
        authRequestTokenMutable.value = requestToken
    }
}
