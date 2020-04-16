package ru.vitalysizov.moviedb.di.base

interface HasComponentDependencies {
    val dependencies: ComponentDependenciesProvider
}