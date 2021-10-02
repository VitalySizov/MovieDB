package ru.vitalysizov.moviedb.presentation.tv_show_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment
import ru.vitalysizov.moviedb.utils.MovieDbComposeTheme
import javax.inject.Inject

class TvShowDetailsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: TvShowDetailsViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MovieDbComposeTheme {
                    TvShowContent(viewModel)
                }
            }
        }
    }
}