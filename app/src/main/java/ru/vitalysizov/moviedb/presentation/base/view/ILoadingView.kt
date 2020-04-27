package ru.vitalysizov.moviedb.presentation.base.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ILoadingView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoading(show: Boolean)

}