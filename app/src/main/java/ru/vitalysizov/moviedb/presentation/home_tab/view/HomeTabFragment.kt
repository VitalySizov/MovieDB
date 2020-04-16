package ru.vitalysizov.moviedb.presentation.home_tab.view

import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.di.base.findComponentDependencies
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment
import ru.vitalysizov.moviedb.presentation.home_tab.di.DaggerHomeTabComponent
import ru.vitalysizov.moviedb.presentation.home_tab.mvp.HomeTabPresenter

class HomeTabFragment : BaseFragment<HomeTabPresenter>(), IHomeTabView {

    override val layoutId: Int
        get() = R.layout.fragment_main_tab

    override fun performInject() {
        DaggerHomeTabComponent.builder()
            .homeTabDependencies(findComponentDependencies())
            .build()
            .inject(this)
    }
}