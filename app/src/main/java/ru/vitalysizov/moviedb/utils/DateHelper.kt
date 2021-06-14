package ru.vitalysizov.moviedb.utils

import org.threeten.bp.LocalDate

object DateHelper {

    fun getLocalDate(date: String?): LocalDate {
        return if (!date.isNullOrEmpty()) {
            LocalDate.parse(date)
        } else {
            LocalDate.of(0, 1, 1)
        }
    }

}