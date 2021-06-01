package ru.vitalysizov.moviedb.presentation.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.databinding.FragmentMainBinding
import ru.vitalysizov.moviedb.presentation.activity.SystemViewModel
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment
import ru.vitalysizov.moviedb.presentation.main.utils.setupWithNavController
import ru.vitalysizov.moviedb.presentation.main.viewmodel.MainViewModel
import ru.vitalysizov.moviedb.utils.*
import javax.inject.Inject

class MainFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mainViewModel: MainViewModel by viewModels(
        ownerProducer = { requireParentFragment() },
        factoryProducer = { viewModelFactory }
    )

    private val systemViewModel: SystemViewModel by activityViewModels()

    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = mainViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBottomNavigation()
        initVisibilityBottomNavigation()
    }

    private fun initBottomNavigation() {
        val mainBottomNavigation = binding.mainBottomNavigation

        val navGraphIds = listOf(
            R.navigation.nav_home_tab_graph,
            R.navigation.nav_search_tab_graph,
            R.navigation.nav_account_tab_graph
        )

        mainBottomNavigation.setupWithNavController(
            navGraphIds,
            childFragmentManager,
            R.id.main_fragment_container
        )

        // Hide(show) bottom navigation when open(close) keyboard
        KeyboardVisibilityEvent.setEventListener(activity) { event ->
            if (event) {
                binding.mainBottomNavigation.gone()
            } else {
                binding.mainBottomNavigation.visible()
            }
        }
    }

    private fun initVisibilityBottomNavigation() {
        val mainBottomNavigation = binding.mainBottomNavigation
        systemViewModel.showBottomNavigation.observe(viewLifecycleOwner, { event ->
            event.getContentIfNotHandled()?.let { isShow ->
                if (mainBottomNavigation.isShown == isShow) {
                    return@let
                }
                if (isShow) {
                    mainBottomNavigation.show()
                } else {
                    mainBottomNavigation.hide()
                }
            }
        })
    }
}