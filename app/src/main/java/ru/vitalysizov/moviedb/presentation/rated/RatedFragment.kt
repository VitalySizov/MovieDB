package ru.vitalysizov.moviedb.presentation.rated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.databinding.FragmentRatedBinding
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment
import ru.vitalysizov.moviedb.presentation.rated.content.RatedTab
import javax.inject.Inject

class RatedFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentRatedBinding

    private val viewModel: RatedViewModel by viewModels(
        ownerProducer = { requireParentFragment() },
        factoryProducer = { viewModelFactory }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rated, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.title = resources.getString(R.string.rated_title)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        val adapter = RatedListAdapter(this)
        val viewPager = binding.viewPagerRatedList

        val tabTitles = RatedTab.values().map { value -> value.tabName }

        viewModel.ratedContent.observe(viewLifecycleOwner, {
            adapter.setRatedContent(it)
            viewPager.adapter = adapter
            val tabLayout = binding.tabLayoutRatedType

            TabLayoutMediator(tabLayout, binding.viewPagerRatedList) { tab, position ->
                tab.text = context?.getString(tabTitles[position])
            }.attach()
        })

    }

}