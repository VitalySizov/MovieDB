package ru.vitalysizov.moviedb.presentation.home_tab.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.Group
import kotlinx.android.synthetic.main.fragment_home_tab.*
import moxy.ktx.moxyPresenter
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.di.base.findComponentDependencies
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment
import ru.vitalysizov.moviedb.presentation.base.view.ILoadingView
import ru.vitalysizov.moviedb.presentation.home_tab.di.DaggerHomeTabComponent
import ru.vitalysizov.moviedb.presentation.home_tab.mvp.HomeTabPresenter
import ru.vitalysizov.moviedb.utils.GroupieAdapter

class HomeTabFragment : BaseFragment<HomeTabPresenter>(), IHomeTabView, ILoadingView {

    private val presenter: HomeTabPresenter by moxyPresenter { lazyPresenter.get() }
    private val homeTabAdapter: GroupieAdapter by lazy { GroupieAdapter() }

    override val layoutId: Int
        get() = R.layout.fragment_home_tab

    override fun performInject() {
        DaggerHomeTabComponent.builder()
            .homeTabDependencies(findComponentDependencies())
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        rv_home.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = homeTabAdapter
        }
    }

    override fun setItems(items: List<Group>) {
        homeTabAdapter.update(items)
    }

    override fun itemChange(position: Int) {
        homeTabAdapter.notifyItemChanged(position)
    }
}