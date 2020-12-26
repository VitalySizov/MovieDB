package ru.vitalysizov.moviedb.presentation.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.databinding.FragmentMainBinding
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment
import ru.vitalysizov.moviedb.presentation.main.utils.setupWithNavController
import ru.vitalysizov.moviedb.presentation.main.viewmodel.MainViewModel
import ru.vitalysizov.moviedb.utils.gone
import ru.vitalysizov.moviedb.utils.visible
import javax.inject.Inject

class MainFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mainViewModel: MainViewModel by viewModels(
        ownerProducer = { requireParentFragment() },
        factoryProducer = { viewModelFactory }
    )

    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        val navigationAdapter = AHBottomNavigationAdapter(activity, R.menu.bottom_navigation_menu)

        val mainBottomNavigation = binding.mainBottomNavigation
        mainBottomNavigation.accentColor =
            ContextCompat.getColor(requireContext(), R.color.colorPrimary)

        navigationAdapter.setupWithBottomNavigation(mainBottomNavigation)

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
        mainBottomNavigation.setupWithNavController(
            navGraphIds,
            bottomItemIds,
            childFragmentManager,
            R.id.main_fragment_container
        )

        // Hide(show) bottom navigation when open(close) keyboard
        KeyboardVisibilityEvent.setEventListener(activity) { event ->
            if (event) {
                mainBottomNavigation.hideBottomNavigation()
                mainBottomNavigation.gone()
            } else {
                mainBottomNavigation.restoreBottomNavigation()
                mainBottomNavigation.visible()
            }
        }
    }
}