package ru.vitalysizov.moviedb.presentation.search_tab.mvp

import moxy.InjectViewState
import ru.vitalysizov.moviedb.presentation.base.mvp.BasePresenter
import ru.vitalysizov.moviedb.presentation.search_tab.view.ISearchTabView
import javax.inject.Inject

@InjectViewState
class SearchTabPresenter @Inject constructor() : BasePresenter<ISearchTabView>() {

}