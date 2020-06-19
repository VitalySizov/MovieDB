package ru.vitalysizov.moviedb.presentation.home_tab.view

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.databinding.FragmentHomeTabBinding
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment
import ru.vitalysizov.moviedb.presentation.home_tab.view.items.HomeTabSection
import ru.vitalysizov.moviedb.presentation.home_tab.viewmodel.HomeTabViewModel
import ru.vitalysizov.moviedb.presentation.movie_details.view.MovieDetailsFragmentArgs
import javax.inject.Inject

class HomeTabFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val homeTabViewModel: HomeTabViewModel by viewModels(
        ownerProducer = { requireParentFragment() },
        factoryProducer = { viewModelFactory }
    )

    private val homeTabSection: HomeTabSection by lazy {
        HomeTabSection(this, homeTabViewModel)
    }

    override val layoutId: Int
        get() = R.layout.fragment_home_tab

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = DataBindingUtil.bind<FragmentHomeTabBinding>(view) ?: return
        initRecyclerView(binding)
    }

    private fun initRecyclerView(binding: FragmentHomeTabBinding) {
        val linearLayoutManager = LinearLayoutManager(context)
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            add(homeTabSection)
        }
        binding.rvHome.layoutManager = linearLayoutManager
        binding.rvHome.adapter = groupAdapter

        homeTabViewModel.click.observe(viewLifecycleOwner, Observer { item ->
            item.getContentIfNotHandled()?.let {
                val args = MovieDetailsFragmentArgs(it)
                findNavController().navigate(
                    R.id.action_homeTabFragment_to_movieDetailsFragment,
                    args.toBundle()
                )
            }
        })
    }
}