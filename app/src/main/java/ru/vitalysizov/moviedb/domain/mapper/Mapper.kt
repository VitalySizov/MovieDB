package ru.vitalysizov.moviedb.domain.mapper

interface Mapper<From, To> {
    fun map(from: From): To
}