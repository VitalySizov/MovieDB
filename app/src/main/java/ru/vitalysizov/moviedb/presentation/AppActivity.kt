package ru.vitalysizov.moviedb.presentation

import android.os.Bundle
import ru.vitalysizov.moviedb.MovieDbApplication
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.presentation.base.view.BaseActivity

class AppActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun performInject() {
        MovieDbApplication.appComponent.inject(this)
    }
}
