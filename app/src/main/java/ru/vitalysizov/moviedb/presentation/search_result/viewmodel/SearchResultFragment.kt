package ru.vitalysizov.moviedb.presentation.search_result.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.databinding.FragmentSearchResultBinding
import ru.vitalysizov.moviedb.model.domain.collection.CollectionItem
import ru.vitalysizov.moviedb.model.domain.companies.CompanyItem
import ru.vitalysizov.moviedb.model.domain.keywords.KeywordItem
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.model.domain.persons.PersonItem
import ru.vitalysizov.moviedb.model.domain.tvShows.TvShowItem
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment
import ru.vitalysizov.moviedb.presentation.movie_details.view.MovieDetailsFragmentArgs
import ru.vitalysizov.moviedb.presentation.search_result.adapters.*
import ru.vitalysizov.moviedb.presentation.search_result.view.SearchResultViewModel
import javax.inject.Inject

class SearchResultFragment : BaseFragment() {

    private lateinit var moviesResultAdapter: SearchMoviesResultAdapter
    private lateinit var tvShowResultAdapter: SearchTvShowResultAdapter
    private lateinit var personsResultAdapter: SearchPersonResultAdapter
    private lateinit var companyResultAdapter: SearchCompanyResultAdapter
    private lateinit var collectionResultAdapter: SearchCollectionResultAdapter
    private lateinit var keywordResultAdapter: SearchKeywordResultAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val searchResultViewModel: SearchResultViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSearchResultBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search_result, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = searchResultViewModel

        initToolbar(binding)
        initAdapters(binding)
        initSearchResultObservers(binding)
        initMovieClickObserver()

        return binding.root
    }

    private fun initToolbar(binding: FragmentSearchResultBinding) {
        binding.includeAppBar.toolbar.apply {
            title = resources.getString(R.string.search_title_mask, searchResultViewModel.query)
        }
        binding.includeAppBar.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initAdapters(binding: FragmentSearchResultBinding) {
        moviesResultAdapter =
            SearchMoviesResultAdapter(
                viewLifecycleOwner,
                searchResultViewModel,
                this::movieItemClickListener
            )
        tvShowResultAdapter = SearchTvShowResultAdapter(
            viewLifecycleOwner,
            searchResultViewModel,
            this::tvShowItemClickListener
        )

        personsResultAdapter = SearchPersonResultAdapter(
            viewLifecycleOwner,
            searchResultViewModel,
            this::personItemClickListener
        )

        companyResultAdapter = SearchCompanyResultAdapter(
            viewLifecycleOwner,
            searchResultViewModel,
            this::companyItemClickListener
        )

        collectionResultAdapter =
            SearchCollectionResultAdapter(
                viewLifecycleOwner,
                searchResultViewModel,
                this::collectionItemClickListener
            )

        keywordResultAdapter = SearchKeywordResultAdapter(
            viewLifecycleOwner,
            searchResultViewModel,
            this::keywordItemClickListener
        )

        val searchResultConcatAdapter = ConcatAdapter(
            moviesResultAdapter,
            tvShowResultAdapter,
            personsResultAdapter,
            companyResultAdapter,
            collectionResultAdapter,
            keywordResultAdapter
        )

        binding.rvSearchResults.adapter = searchResultConcatAdapter
    }

    private fun initSearchResultObservers(binding: FragmentSearchResultBinding) {
        searchResultViewModel.searchResultContent.observe(viewLifecycleOwner) { items ->
            items.let {
                moviesResultAdapter.generateDataWithHeaderAndFooter(items.movies.result)
                tvShowResultAdapter.generateDataWithHeaderAndFooter(items.tvShows.result)
                personsResultAdapter.generateDataWithHeaderAndFooter(items.persons.result)
                companyResultAdapter.generateDataWithHeaderAndFooter(items.companies.result)
                collectionResultAdapter.generateDataWithHeaderAndFooter(items.collections.result)
                keywordResultAdapter.generateDataWithHeaderAndFooter(items.keywords.result)
            }
        }
    }

    private fun initMovieClickObserver() {
        searchResultViewModel.movieDetailsClick.observe(viewLifecycleOwner, Observer { item ->
            item.getContentIfNotHandled()?.let {
                findNavController().navigate(
                    R.id.action_global_movieDetailsFragment,
                    MovieDetailsFragmentArgs(it).toBundle()
                )
            }
        })
    }

    private fun movieItemClickListener(item: MovieItem) {
        searchResultViewModel.setMovieDetailsClick(item.id)
    }

    private fun tvShowItemClickListener(item: TvShowItem) {
        //TODO
    }

    private fun personItemClickListener(item: PersonItem) {
        //TODO
    }

    private fun companyItemClickListener(item: CompanyItem) {
        //TODO
    }

    private fun collectionItemClickListener(item: CollectionItem) {
        //TODO
    }

    private fun keywordItemClickListener(item: KeywordItem) {
        //TODO
    }

}
