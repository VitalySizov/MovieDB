package ru.vitalysizov.moviedb.presentation.rated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import okhttp3.internal.addHeaderLenient
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.databinding.FragmentListRatedBinding
import ru.vitalysizov.moviedb.presentation.base.adapter.goneUnless
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment
import ru.vitalysizov.moviedb.presentation.rated.adapter.RatedAdapter
import javax.inject.Inject

class RatedListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentListRatedBinding

    private val viewModel: RatedListViewModel by viewModels(factoryProducer = { viewModelFactory })
    private val ratedAdapter = RatedAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_rated, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvRatedItems.adapter = ratedAdapter
        initTabDataObservers()
    }

    private fun initTabDataObservers() {
        viewModel.movies.observe(viewLifecycleOwner) {
            ratedAdapter.submitList(it)
            binding.tvEmptyList.goneUnless(it.isEmpty())
        }

        viewModel.tvShows.observe(viewLifecycleOwner) {
            ratedAdapter.submitList(it)
            binding.tvEmptyList.goneUnless(it.isEmpty())
        }

        viewModel.tvEpisodes.observe(viewLifecycleOwner) {
            ratedAdapter.submitList(it)
            binding.tvEmptyList.goneUnless(it.isEmpty())
        }
    }

}