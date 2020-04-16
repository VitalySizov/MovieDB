package ru.vitalysizov.moviedb.presentation.account_tab.mvp

import moxy.InjectViewState
import ru.vitalysizov.moviedb.presentation.account_tab.view.IAccountTabView
import ru.vitalysizov.moviedb.presentation.base.mvp.BasePresenter
import javax.inject.Inject

@InjectViewState
class AccountTabPresenter @Inject constructor() : BasePresenter<IAccountTabView>() {

}