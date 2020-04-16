package ru.vitalysizov.moviedb.presentation.main.view

import android.os.Bundle
import android.view.View
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.di.base.findComponentDependencies
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment
import ru.vitalysizov.moviedb.presentation.main.di.DaggerMainComponent
import ru.vitalysizov.moviedb.presentation.main.mvp.MainPresenter
import ru.vitalysizov.moviedb.presentation.main.utils.setupWithNavController

class MainFragment : BaseFragment<MainPresenter>(), IMainView {

    override val layoutId: Int
        get() = R.layout.fragment_main

    override fun performInject() {
        DaggerMainComponent.builder()
            .mainDependencies(findComponentDependencies())
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        val navigationAdapter =
            AHBottomNavigationAdapter(appActivity, R.menu.bottom_navigation_menu)
        navigationAdapter.setupWithBottomNavigation(main_bottom_navigation)

        val navGraphIds = listOf(
            R.navigation.nav_main_tab_graph,
            R.navigation.nav_search_tab_graph,
            R.navigation.nav_account_tab_graph
        )
        val bottomItemIds = listOf(
            R.id.main_tab,
            R.id.search_tab,
            R.id.account_tab
        )
        main_bottom_navigation.setupWithNavController(
            navGraphIds,
            bottomItemIds,
            childFragmentManager,
            R.id.main_fragment_container
        )
    }
}