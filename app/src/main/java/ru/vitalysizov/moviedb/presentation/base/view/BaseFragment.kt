package ru.vitalysizov.moviedb.presentation.base.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.Lazy
import moxy.MvpAppCompatFragment
import ru.vitalysizov.moviedb.presentation.base.mvp.BasePresenter
import javax.inject.Inject

abstract class BaseFragment<Presenter : BasePresenter<*>> : MvpAppCompatFragment(), IBaseView {

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
}