package ru.vitalysizov.moviedb.presentation.base

interface ItemClickListener<T> {
    fun onClickListener(item: T)
}