package ru.vitalysizov.moviedb.presentation.common


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.model.domain.castAndCrew.CastAndCrewItem
import ru.vitalysizov.moviedb.model.domain.castAndCrew.CastItem

@Composable
fun CastHorizontalList(cast: List<CastItem>, onAllCastClicked: () -> Unit) {
    Column() {
        CastHeader(onAllCastClicked)
        CastList(cast)
    }
}

@Composable
fun CastHeader(onAllCastClicked: () -> Unit) {
    Row(Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 8.dp)) {
        Text(
            text = stringResource(id = R.string.cast_header),
            modifier = Modifier.weight(2f, true)
        )
        Text(
            text = stringResource(id = R.string.cast_all),
            modifier = Modifier
                .align(Alignment.Bottom)
                .clickable {
                    onAllCastClicked.invoke()
                }
                .wrapContentWidth(align = Alignment.End)
        )
    }
}

@Composable
fun CastList(cast: List<CastItem>) {
    LazyRow(contentPadding = PaddingValues(start = 8.dp)) {
        cast.forEach { castItem ->
            item {
                Spacer(modifier = Modifier.padding(start = 8.dp))
                CastItem(castItem = castItem)
            }
        }
        item { Spacer(modifier = Modifier.padding(start = 16.dp)) }
    }
}


@Composable
fun CastItem(castItem: CastItem) {
    Column(modifier = Modifier
        .width(100.dp)
        .clickable {

        }) {
        Image(
            painter = rememberImagePainter(castItem.profilePath.ifEmpty { R.drawable.ic_baseline_person_24 }),
            contentDescription = null,
            modifier = Modifier.size(width = 100.dp, height = 150.dp),
            contentScale = if (castItem.profilePath.isEmpty()) ContentScale.Crop else ContentScale.Fit
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(Color.Black)) {
                    append(castItem.name)
                    append("\n")
                }
                withStyle(style = SpanStyle(Color.Gray)) {
                    append(castItem.character)
                }
            },
            fontSize = 14.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )
    }
}