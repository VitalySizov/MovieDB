package ru.vitalysizov.moviedb.presentation.base.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import dagger.android.support.DaggerFragment
import ru.vitalysizov.moviedb.presentation.activity.AppActivity
import ru.vitalysizov.moviedb.presentation.activity.SystemViewModel

abstract class BaseFragment : DaggerFragment() {

    private lateinit var appActivity: AppActivity

    private val systemViewModel: SystemViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appActivity = context as AppActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDefaultVisibleBottomNavigation()
    }

    private fun initDefaultVisibleBottomNavigation() {
        systemViewModel.onShowBottomNavigation()
    }

}