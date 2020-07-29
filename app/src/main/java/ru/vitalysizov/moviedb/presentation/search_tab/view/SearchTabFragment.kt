package ru.vitalysizov.moviedb.presentation.search_tab.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.databinding.FragmentSearchTabBinding
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.model.domain.persons.PersonItem
import ru.vitalysizov.moviedb.model.domain.tvShows.TvShowItem
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment
import ru.vitalysizov.moviedb.presentation.movie_details.view.MovieDetailsFragmentArgs
import ru.vitalysizov.moviedb.presentation.search_result.viewmodel.SearchResultFragmentArgs
import ru.vitalysizov.moviedb.presentation.search_tab.adapters.SearchTabAdapter
import ru.vitalysizov.moviedb.presentation.search_tab.viewmodel.SearchTabViewModel
import ru.vitalysizov.moviedb.utils.dismissKeyboard
import javax.inject.Inject

class SearchTabFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val searchTabViewModel: SearchTabViewModel by viewModels(
        ownerProducer = { requireParentFragment() },
        factoryProducer = { viewModelFactory }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSearchTabBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search_tab, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = searchTabViewModel

        initAdapter(binding)
        initSearchTabObservers(binding)

        return binding.root
    }

    private fun initAdapter(binding: FragmentSearchTabBinding) {
        val searchAdapter = SearchTabAdapter(
            viewLifecycleOwner,
            this::actionResultsDetails,
            this::onClickMovie,
            this::onClickTvShow,
            this::onClickPerson
        )

        binding.rvSearchResults.adapter = searchAdapter

        searchTabViewModel.searchResult.observe(viewLifecycleOwner) { items ->
            items.let {
                searchAdapter.items = items
            }
        }
    }

    private fun actionResultsDetails() {
        searchTabViewModel.setSearchResultsDetailsClick()
    }

    private fun onClickMovie(item: MovieItem) {
        searchTabViewModel.setMovieDetailsClick(item.id)
    }

    private fun onClickTvShow(item: TvShowItem) {

    }

    private fun onClickPerson(item: PersonItem) {

    }

    private fun initSearchTabObservers(binding: FragmentSearchTabBinding) {
        searchTabViewModel.searchResultsDetailsClick.observe(viewLifecycleOwner, Observer { item ->
            item.getContentIfNotHandled()?.let {
                dismissKeyboard(binding.root)
                val args = SearchResultFragmentArgs(it)
                findNavController().navigate(
                    R.id.action_searchTabFragment_to_searchResultFragment,
                    args.toBundle()
                )
            }
        })

        searchTabViewModel.movieDetailsClick.observe(viewLifecycleOwner, Observer { item ->
            item.getContentIfNotHandled()?.let {
                dismissKeyboard(binding.root)
                val args = MovieDetailsFragmentArgs(it)
                findNavController().navigate(
                    R.id.action_searchTabFragment_to_movieDetailsFragment2,
                    args.toBundle()
                )
            }
        })

        binding.includeSearchAppbar.searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    actionResultsDetails()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    searchTabViewModel.onSearchQueryChanged(newText)
                    return true
                }
            })
        }
    }
}