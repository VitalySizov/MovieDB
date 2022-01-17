package ru.vitalysizov.moviedb.presentation.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.vitalysizov.moviedb.BuildConfig

abstract class BaseViewModel : ViewModel() {

    val loading: LiveData<Boolean> get() = loadingMutable
    private val loadingMutable = MutableLiveData<Boolean>()

    private val isShowBottomNavigation = MutableLiveData<Boolean>(true)

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
        loadingMutable.value = true
    }

    fun hideLoading() {
        loadingMutable.value = false
    }

    fun showBottomNavigation() {
        isShowBottomNavigation.value = true
    }

    fun hideBottomNavigation() {
        isShowBottomNavigation.value = false
    }
}