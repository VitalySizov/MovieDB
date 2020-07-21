package ru.vitalysizov.moviedb.presentation.base.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import ru.vitalysizov.moviedb.presentation.AppActivity

abstract class BaseFragment : DaggerFragment() {

    private lateinit var appActivity: AppActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appActivity = context as AppActivity
    }

//    override fun showLoading(show: Boolean) {
//        view?.findViewById<FrameLayout>(R.id.loading_container)?.visibleOrGone(show)
//    }
}