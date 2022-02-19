package ru.vitalysizov.moviedb.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@ExperimentalCoilApi
@Composable
fun PosterAndOverview(posterPath: String, overview: String, onOverviewDetailsClicked: () -> Unit) {
    Row(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        Image(
            painter = rememberImagePainter(posterPath),
            contentDescription = null,
            modifier = Modifier
                .width(width = 120.dp)
                .height(height = 180.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            overflow = TextOverflow.Ellipsis,
            text = AnnotatedString(overview),
            maxLines = 9,
            modifier = Modifier
                .align(Alignment.Top)
                .clickable { onOverviewDetailsClicked.invoke() }
                .padding(start = 8.dp, end = 8.dp)
                .fillMaxHeight(),
        )
    }
}

