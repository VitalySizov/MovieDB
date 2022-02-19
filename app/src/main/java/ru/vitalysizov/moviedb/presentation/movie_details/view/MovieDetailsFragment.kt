package ru.vitalysizov.moviedb.presentation.movie_details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment
import ru.vitalysizov.moviedb.presentation.movie_details.MovieDetailsContent
import ru.vitalysizov.moviedb.presentation.movie_details.viewmodel.MovieDetailsViewModel
import ru.vitalysizov.moviedb.utils.MovieDbComposeTheme
import javax.inject.Inject

class MovieDetailsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MovieDetailsViewModel by viewModels { viewModelFactory }

    @ExperimentalPagerApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MovieDbComposeTheme {
                    MovieDetailsContent(viewModel)
                }
            }
        }
    }

}