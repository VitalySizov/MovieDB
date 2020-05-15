package ru.vitalysizov.moviedb.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.presentation.base.view.BaseActivity
import javax.inject.Inject

class AppActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
