package ru.vitalysizov.moviedb.presentation.splash.di

import dagger.Component
import ru.vitalysizov.moviedb.presentation.splash.dependencies.SplashDependencies
import ru.vitalysizov.moviedb.presentation.splash.view.SplashFragment

@Component(dependencies = [SplashDependencies::class])
interface SplashComponent {

    fun inject(fragment: SplashFragment)
}