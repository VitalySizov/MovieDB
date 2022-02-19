package ru.vitalysizov.moviedb.presentation.common

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import ru.vitalysizov.moviedb.R

@Composable
fun DefaultDivider() {
    Divider(
        color = colorResource(id = R.color.gray),
        thickness = 0.5.dp
    )
}



