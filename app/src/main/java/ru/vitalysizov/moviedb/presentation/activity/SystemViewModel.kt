package ru.vitalysizov.moviedb.presentation.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.vitalysizov.moviedb.utils.Event
import javax.inject.Inject

class SystemViewModel @Inject constructor() : ViewModel() {

    private val authRequestTokenMutable = MutableLiveData<Event<String>>()
    val authRequestToken: LiveData<Event<String>> get() = authRequestTokenMutable

    fun setAuthRequestToken(requestToken: String) {
        authRequestTokenMutable.value = Event(requestToken)
    }
}
