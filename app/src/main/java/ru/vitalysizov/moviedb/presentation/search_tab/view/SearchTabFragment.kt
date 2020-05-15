package ru.vitalysizov.moviedb.presentation.search_tab.view

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment
import ru.vitalysizov.moviedb.presentation.search_tab.viewmodel.SearchTabViewModel
import javax.inject.Inject

class SearchTabFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val searchTabViewModel: SearchTabViewModel by viewModels(
        ownerProducer = { requireParentFragment() },
        factoryProducer = { viewModelFactory }
    )

    override val layoutId: Int
        get() = R.layout.fragment_search_tab

}