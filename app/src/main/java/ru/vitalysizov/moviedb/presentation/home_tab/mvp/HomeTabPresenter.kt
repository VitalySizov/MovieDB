package ru.vitalysizov.moviedb.presentation.home_tab.mvp

import moxy.InjectViewState
import ru.vitalysizov.moviedb.presentation.base.mvp.BasePresenter
import ru.vitalysizov.moviedb.presentation.main.view.IMainView
import javax.inject.Inject

@InjectViewState
class HomeTabPresenter @Inject constructor(): BasePresenter<IMainView>() {

}