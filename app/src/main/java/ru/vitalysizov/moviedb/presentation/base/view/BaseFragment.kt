package ru.vitalysizov.moviedb.presentation.base.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import dagger.Lazy
import moxy.MvpAppCompatFragment
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.presentation.base.mvp.BasePresenter
import ru.vitalysizov.moviedb.utils.visibleOrGone
import javax.inject.Inject

abstract class BaseFragment<Presenter : BasePresenter<*>> : MvpAppCompatFragment(), IBaseView,
    ILoadingView {

    abstract val layoutId: Int

    @Inject
    open lateinit var lazyPresenter: Lazy<Presenter>

    abstract fun performInject()

    protected lateinit var appActivity: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        performInject()
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appActivity = context as BaseActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun showLoading(show: Boolean) {
        view?.findViewById<FrameLayout>(R.id.loading_container)?.visibleOrGone(show)
    }
}