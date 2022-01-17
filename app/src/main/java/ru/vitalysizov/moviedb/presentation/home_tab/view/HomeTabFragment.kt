package ru.vitalysizov.moviedb.presentation.home_tab.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment
import ru.vitalysizov.moviedb.presentation.home_tab.HomeTabContent
import ru.vitalysizov.moviedb.presentation.home_tab.viewmodel.HomeTabViewModel
import ru.vitalysizov.moviedb.presentation.movie_details.view.MovieDetailsFragmentArgs
import ru.vitalysizov.moviedb.presentation.tv_show_details.TvShowDetailsFragmentArgs
import ru.vitalysizov.moviedb.utils.MovieDbComposeTheme
import javax.inject.Inject

class HomeTabFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeTabViewModel by viewModels(
        ownerProducer = { requireParentFragment() },
        factoryProducer = { viewModelFactory }
    )

    @ExperimentalPagerApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MovieDbComposeTheme {
                    HomeTabContent(viewModel)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO rework navigation
        viewModel.navigateToMovieDetails.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                findNavController().navigate(
                    R.id.action_global_movieDetailsFragment,
                    MovieDetailsFragmentArgs(it.id).toBundle()
                )
            }
        }

        viewModel.navigateToTvShowDetails.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                findNavController().navigate(
                    R.id.action_global_tvShowDetailsFragment,
                    TvShowDetailsFragmentArgs(it.id).toBundle()
                )
            }
        }
    }

}