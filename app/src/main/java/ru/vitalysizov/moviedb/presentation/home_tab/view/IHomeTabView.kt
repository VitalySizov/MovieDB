package ru.vitalysizov.moviedb.presentation.home_tab.view

import com.xwray.groupie.Group
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.vitalysizov.moviedb.presentation.base.view.IBaseView
import ru.vitalysizov.moviedb.presentation.base.view.ILoadingView

interface IHomeTabView : IBaseView, ILoadingView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setItems(items: List<Group>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun itemChange(position: Int)
}