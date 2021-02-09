package ru.vitalysizov.moviedb.presentation.base.view

import android.content.Context
import dagger.android.support.DaggerFragment
import ru.vitalysizov.moviedb.presentation.activity.AppActivity

abstract class BaseFragment : DaggerFragment() {

    private lateinit var appActivity: AppActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appActivity = context as AppActivity
    }

}