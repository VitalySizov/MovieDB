package ru.vitalysizov.moviedb.presentation.main.di

import dagger.Component
import ru.vitalysizov.moviedb.presentation.main.dependencies.MainDependencies
import ru.vitalysizov.moviedb.presentation.main.view.MainFragment

@Component(dependencies = [MainDependencies::class])
interface MainComponent {

    fun inject(fragment: MainFragment)
}