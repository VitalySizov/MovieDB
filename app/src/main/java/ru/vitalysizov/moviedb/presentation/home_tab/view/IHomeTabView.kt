package ru.vitalysizov.moviedb.presentation.home_tab.view

import com.xwray.groupie.Group
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.vitalysizov.moviedb.presentation.base.view.IBaseView

interface IHomeTabView : IBaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setItems(items: List<Group>)
}