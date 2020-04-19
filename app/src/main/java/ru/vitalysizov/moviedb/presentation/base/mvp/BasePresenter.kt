package ru.vitalysizov.moviedb.presentation.base.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import ru.vitalysizov.moviedb.BuildConfig
import ru.vitalysizov.moviedb.presentation.base.view.IBaseView

abstract class BasePresenter<View : IBaseView> : MvpPresenter<View>() {

    private val compositeDisposable = CompositeDisposable()

    fun launch(disposable: () -> Disposable) {
        compositeDisposable.add(disposable.invoke())
    }

    fun handleError(t: Throwable) {
        if (BuildConfig.DEBUG) {
            t.printStackTrace()
        }
    }
}