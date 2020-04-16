package ru.vitalysizov.moviedb.presentation.search_tab.view

import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.di.base.findComponentDependencies
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment
import ru.vitalysizov.moviedb.presentation.search_tab.di.DaggerSearchTabComponent
import ru.vitalysizov.moviedb.presentation.search_tab.mvp.SearchTabPresenter

class SearchTabFragment : BaseFragment<SearchTabPresenter>(), ISearchTabView {

    override val layoutId: Int
        get() = R.layout.fragment_search_tab

    override fun performInject() {
        DaggerSearchTabComponent.builder()
            .searchTabDependencies(findComponentDependencies())
            .build()
            .inject(this)
    }
}