package ru.vitalysizov.moviedb.presentation.splash.mvp

import moxy.InjectViewState
import ru.vitalysizov.moviedb.presentation.base.mvp.BasePresenter
import ru.vitalysizov.moviedb.presentation.splash.view.ISplashView
import javax.inject.Inject

@InjectViewState
class SplashPresenter @Inject constructor() : BasePresenter<ISplashView>() {

}