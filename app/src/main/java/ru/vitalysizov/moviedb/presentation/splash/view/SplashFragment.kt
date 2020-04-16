package ru.vitalysizov.moviedb.presentation.splash.view

import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.di.base.findComponentDependencies
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment
import ru.vitalysizov.moviedb.presentation.splash.di.DaggerSplashComponent
import ru.vitalysizov.moviedb.presentation.splash.mvp.SplashPresenter

class SplashFragment : BaseFragment<SplashPresenter>(), ISplashView {

    override val layoutId: Int
        get() = R.layout.fragment_splash

    override fun performInject() {
        DaggerSplashComponent.builder()
            .splashDependencies(findComponentDependencies())
            .build()
            .inject(this)
    }
}