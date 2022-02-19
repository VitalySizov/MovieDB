package ru.vitalysizov.moviedb.presentation.common

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.model.domain.castAndCrew.CrewItem
import ru.vitalysizov.moviedb.presentation.movie_details.adapters.DEPARTMENT_DIRECTING
import ru.vitalysizov.moviedb.presentation.movie_details.adapters.DEPARTMENT_WRITING
import ru.vitalysizov.moviedb.presentation.movie_details.adapters.JOB_DIRECTOR

@Composable
fun CrewDirectors(items: List<CrewItem>, onDirectorsClicked: (List<CrewItem>) -> Unit) {
    if (items.isEmpty()) return

    //TODO: in mapper
    val directors = items.filter {
        it.department.contains(DEPARTMENT_DIRECTING).and(it.job == JOB_DIRECTOR)
    }
    val directorsNames = directors.map { it.name }.toSet().joinToString { it }
    BaseCrewItem(header = R.string.directors, items = directorsNames)
}

@Composable
fun CrewWriters(items: List<CrewItem>) {
    //TODO: in mapper
    val writers = items.filter { it.department.contains(DEPARTMENT_WRITING) }
    val writersNames = writers.map { it.name }.toSet().joinToString { it }
    BaseCrewItem(header = R.string.writers, items = writersNames)
}

@Composable
fun BaseCrewItem(@StringRes header: Int, items: String) {
    Column(modifier = Modifier
        .padding(start = 16.dp, end = 16.dp)
        .fillMaxWidth()
        .clickable { }
    ) {
        Text(text = stringResource(header), fontSize = 16.sp)
        Text(text = items, fontSize = 14.sp, color = Color.Gray)
    }
}

