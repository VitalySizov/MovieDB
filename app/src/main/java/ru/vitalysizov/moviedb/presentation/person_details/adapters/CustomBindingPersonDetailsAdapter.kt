package ru.vitalysizov.moviedb.presentation.person_details.adapters

import android.widget.ImageButton
import android.widget.TextView
import androidx.databinding.BindingAdapter
import org.threeten.bp.LocalDate
import org.threeten.bp.Period
import org.threeten.bp.format.DateTimeFormatter
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.model.domain.person.PersonDetails
import ru.vitalysizov.moviedb.utils.gone
import ru.vitalysizov.moviedb.utils.visible
import java.util.*

@BindingAdapter(value = ["setYearsOfLife"])
fun TextView.setYearsOfLife(personDetails: PersonDetails?) {

    val birthDay = personDetails?.birthDay
    val deathDay = personDetails?.deathDay

    if (birthDay != null && birthDay.year != 0 && deathDay != null && deathDay.year != 0) {
        val years = Period.between(birthDay, deathDay).years.toString()
        this.text = resources.getString(
            R.string.birthday_with_deathDay_mask,
            birthDay.format(DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.getDefault())),
            deathDay.format(DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.getDefault())), years
        )
        visible()
        return
    }

    if (birthDay != null && birthDay.year != 0) {
        val currentDate = LocalDate.now()
        val years = Period.between(birthDay, currentDate).years.toString()
        this.text = resources.getString(
            R.string.birthday_without_deathDay_mask,
            birthDay.format(DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.getDefault())),
            years
        )
        visible()
    } else {
        gone()
    }
}

@BindingAdapter(value = ["setVisibleSocialNetwork"])
fun ImageButton.setVisibleFacebook(id: String?) {
    if (id.isNullOrEmpty()) {
        gone()
    } else {
        visible()
    }
}
