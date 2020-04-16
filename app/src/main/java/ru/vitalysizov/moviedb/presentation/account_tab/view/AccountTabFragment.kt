package ru.vitalysizov.moviedb.presentation.account_tab.view

import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.di.base.findComponentDependencies
import ru.vitalysizov.moviedb.presentation.account_tab.di.DaggerAccountTabComponent
import ru.vitalysizov.moviedb.presentation.account_tab.mvp.AccountTabPresenter
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment

class AccountTabFragment : BaseFragment<AccountTabPresenter>(), IAccountTabView {

    override val layoutId: Int
        get() = R.layout.fragment_account_tab

    override fun performInject() {
        DaggerAccountTabComponent.builder()
            .accountTabDependencies(findComponentDependencies())
            .build()
            .inject(this)
    }
}