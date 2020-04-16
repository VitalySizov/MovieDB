package ru.vitalysizov.moviedb.presentation.main.mvp

import moxy.InjectViewState
import ru.vitalysizov.moviedb.presentation.base.mvp.BasePresenter
import ru.vitalysizov.moviedb.presentation.main.view.IMainView
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor() : BasePresenter<IMainView>() {

}