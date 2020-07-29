package ru.vitalysizov.moviedb.presentation.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.vitalysizov.moviedb.BuildConfig

abstract class BaseViewModel : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    private val compositeDisposable = CompositeDisposable()

    fun launch(disposable: () -> Disposable) {
        compositeDisposable.add(disposable.invoke())
    }

    fun handleError(t: Throwable) {
        if (BuildConfig.DEBUG) {
            t.printStackTrace()
            hideLoading()
        }
    }

    fun showLoading() {
        loading.value = true
    }

    fun hideLoading() {
        loading.value = false
    }
}