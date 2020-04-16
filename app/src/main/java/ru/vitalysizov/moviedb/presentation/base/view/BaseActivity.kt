package ru.vitalysizov.moviedb.presentation.base.view

import android.os.Bundle
import moxy.MvpAppCompatActivity
import ru.vitalysizov.moviedb.di.base.ComponentDependenciesProvider
import ru.vitalysizov.moviedb.di.base.HasComponentDependencies
import javax.inject.Inject

abstract class BaseActivity : MvpAppCompatActivity(), HasComponentDependencies {

    @Inject
    override lateinit var dependencies: ComponentDependenciesProvider

    abstract fun performInject()

    override fun onCreate(savedInstanceState: Bundle?) {
        performInject()
        super.onCreate(savedInstanceState)
    }
}