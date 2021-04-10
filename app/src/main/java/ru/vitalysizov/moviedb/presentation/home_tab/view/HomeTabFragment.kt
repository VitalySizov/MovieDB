package ru.vitalysizov.moviedb.presentation.home_tab.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.databinding.FragmentHomeTabBinding
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.presentation.base.ItemClickListener
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment
import ru.vitalysizov.moviedb.presentation.home_tab.adapters.CarouselGenresAdapter
import ru.vitalysizov.moviedb.presentation.home_tab.adapters.CarouselMoviesAdapter
import ru.vitalysizov.moviedb.presentation.home_tab.adapters.CarouselMoviesTrendingAdapter
import ru.vitalysizov.moviedb.presentation.home_tab.viewmodel.HomeTabViewModel
import ru.vitalysizov.moviedb.presentation.movie_details.view.MovieDetailsFragmentArgs
import ru.vitalysizov.moviedb.utils.ScrollStateHolder
import javax.inject.Inject

class HomeTabFragment : BaseFragment(), ItemClickListener<MovieItem> {

    private lateinit var inTheatersMoviesAdapter: CarouselMoviesAdapter
    private lateinit var popMoviesMoviesAdapter: CarouselMoviesAdapter
    private lateinit var genresAdapter: CarouselGenresAdapter
    private lateinit var moviesTrendingAdapter: CarouselMoviesTrendingAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val homeTabViewModel: HomeTabViewModel by viewModels(
        ownerProducer = { requireParentFragment() },
        factoryProducer = { viewModelFactory }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapters(savedInstanceState)
    }

    private fun initAdapters(savedInstanceState: Bundle?) {
        inTheatersMoviesAdapter = CarouselMoviesAdapter(ScrollStateHolder(savedInstanceState), this)
        popMoviesMoviesAdapter = CarouselMoviesAdapter(ScrollStateHolder(savedInstanceState), this)
        genresAdapter = CarouselGenresAdapter(ScrollStateHolder(savedInstanceState))
        moviesTrendingAdapter =
            CarouselMoviesTrendingAdapter(ScrollStateHolder(savedInstanceState), this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentHomeTabBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home_tab, container, false)

        initHomeTabAdapter(binding)
        initHomeTabDataObservers()
        initMovieClickObserver()

        binding.lifecycleOwner = this
        binding.viewModel = homeTabViewModel

        return binding.root
    }

    private fun initHomeTabAdapter(binding: FragmentHomeTabBinding) {
        val concatAdapter = ConcatAdapter(
            inTheatersMoviesAdapter,
            popMoviesMoviesAdapter,
            genresAdapter,
            moviesTrendingAdapter
        )
        binding.rvHome.adapter = concatAdapter
    }

    private fun initMovieClickObserver() {
        homeTabViewModel.movieDetailsClick.observe(viewLifecycleOwner) { item ->
            item.getContentIfNotHandled()?.let {
                val args = MovieDetailsFragmentArgs(it)
                findNavController().navigate(
                    R.id.action_homeTabFragment_to_movieDetailsFragment,
                    args.toBundle()
                )
            }
        }
    }

    private fun initHomeTabDataObservers() {
        homeTabViewModel.inTheatersMoviesCategory.observe(viewLifecycleOwner) { items ->
            items.let {
                inTheatersMoviesAdapter.submitList(it)
            }
        }

        homeTabViewModel.popMoviesCategory.observe(viewLifecycleOwner) { items ->
            items.let {
                popMoviesMoviesAdapter.submitList(items)
            }
        }

        homeTabViewModel.genresCategory.observe(viewLifecycleOwner) { items ->
            items.let {
                genresAdapter.submitList(items)
            }
        }

        homeTabViewModel.moviesTrendingCategory.observe(viewLifecycleOwner) { items ->
            items.let {
                moviesTrendingAdapter.submitList(items)
            }
        }
    }

    override fun onClickListener(item: MovieItem) {
        homeTabViewModel.setMovieDetailsClick(item.id)
    }
}