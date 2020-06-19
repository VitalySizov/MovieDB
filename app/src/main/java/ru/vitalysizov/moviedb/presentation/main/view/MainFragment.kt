package ru.vitalysizov.moviedb.presentation.main.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment
import ru.vitalysizov.moviedb.presentation.main.utils.setupWithNavController
import javax.inject.Inject

class MainFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val layoutId: Int
        get() = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        val navigationAdapter = AHBottomNavigationAdapter(activity, R.menu.bottom_navigation_menu)
        navigationAdapter.setupWithBottomNavigation(main_bottom_navigation)

        val navGraphIds = listOf(
            R.navigation.nav_home_tab_graph,
            R.navigation.nav_search_tab_graph,
            R.navigation.nav_account_tab_graph
        )
        val bottomItemIds = listOf(
            R.id.home_tab,
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