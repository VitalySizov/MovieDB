package ru.vitalysizov.moviedb.presentation.tv_show_details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberImagePainter
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.model.domain.GenreItem
import ru.vitalysizov.moviedb.model.domain.castAndCrew.CastAndCrewItem
import ru.vitalysizov.moviedb.model.domain.castAndCrew.CastItem
import ru.vitalysizov.moviedb.model.domain.tvShows.TvShowDetailsItem

@Composable
fun TvShowContent(
    viewModel: TvShowDetailsViewModel,
) {
    val details by viewModel.tvShowDetailsScreenContent.observeAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        details?.let { content ->
            item {
                Image(
                    painter = rememberImagePainter(
                        content.tvShowDetailsItem.backdropPath
                    ),
                    contentDescription = null,
                    modifier = Modifier.height(300.dp),
                    contentScale = ContentScale.Crop
                )
            }
            item {
                TvShowDetailsShortInfo(content.tvShowDetailsItem)
            }
            item {
                Divider(
                    color = colorResource(id = R.color.gray),
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                )
            }
            item {
                TvShowDetailsPosterAndDescription(content.tvShowDetailsItem)
            }
            item {
                Divider(
                    color = colorResource(id = R.color.gray),
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                )
            }
            item {
                TvShowRating(content.tvShowDetailsItem)
            }
            item {
                TvShowCastHeader()
            }
            item {
                TvShowCastList(content.castAndCrewItem)
            }
        }
    }
}

@Composable
fun TvShowDetailsShortInfo(details: TvShowDetailsItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(top = 16.dp)) {
            Text(text = details.voteAverage)
            Text(text = details.name)
        }
        Text(text = details.originalName)
        Text(text = details.getActiveYears())
        Text(text = details.getInfo())
    }
}

@Composable
fun TvShowDetailsPosterAndDescription(details: TvShowDetailsItem) {
    ConstraintLayout(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        val (imagePoster, genresList, textOverview, buttonAddToFavorite) = createRefs()
        Image(
            painter = rememberImagePainter(details.posterPath),
            contentDescription = null,
            modifier = Modifier
                .size(width = 120.dp, height = 180.dp)
                .constrainAs(imagePoster) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            contentScale = ContentScale.Crop
        )
        TvShowGenresList(
            genres = details.genres,
            modifier = Modifier.constrainAs(genresList) {
                start.linkTo(imagePoster.end, margin = 8.dp)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )
        Text(
            text = details.overview,
            modifier = Modifier
                .constrainAs(textOverview) {
                    top.linkTo(genresList.bottom)
                    start.linkTo(imagePoster.end, margin = 8.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                    bottom.linkTo(imagePoster.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.preferredWrapContent
                },
        )

        Button(
            onClick = {
                //TODO: add to favorite
            },
            modifier = Modifier
                .constrainAs(buttonAddToFavorite) {
                    top.linkTo(imagePoster.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.wrapContent
                    width = Dimension.fillToConstraints
                }
                .padding(top = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.button_add_to_favorite))
        }
    }
}

@Composable
fun TvShowGenresList(genres: List<GenreItem>, modifier: Modifier) {
    LazyRow(
        modifier = modifier,
    ) {
        for (genre in genres) {
            item {
                TvShowGenreItem(genre.name)
            }
        }
    }
}

@Composable
fun TvShowGenreItem(name: String) {
    Card(
        modifier = Modifier.padding(end = 8.dp),
        border = BorderStroke(color = colorResource(id = R.color.gray), width = Dp.Hairline),
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = name)
        }
    }
}

@Composable
fun TvShowRating(details: TvShowDetailsItem) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Text(text = stringResource(id = R.string.title_rating_the_movie_db))
        Card(
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth(),
            border = BorderStroke(color = colorResource(id = R.color.gray), width = Dp.Hairline),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = details.voteAverage)
                Text(
                    text = LocalContext.current.resources.getQuantityString(
                        R.plurals.vote_count_plurals,
                        details.voteCount,
                        details.voteCount
                    )
                )
                Button(onClick = {
                    //TODO: navigate to estimate
                }) {
                    Text(text = stringResource(id = R.string.button_estimate))
                }
            }
        }
    }
}

@Composable
fun TvShowCastHeader() {
    Row(Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 8.dp)) {
        Text(
            text = stringResource(id = R.string.cast_header),
            modifier = Modifier.weight(2f, true)
        )
        ClickableText(
            modifier = Modifier
                .align(Alignment.Bottom)
                .weight(1f)
                .wrapContentWidth(align = Alignment.End),
            text = AnnotatedString(stringResource(id = R.string.cast_all)),
            onClick = {
                //TODO: navigate to all cast
            }
        )
    }
}

@Composable
fun TvShowCastList(castAndCrewItem: CastAndCrewItem) {
    LazyRow(contentPadding = PaddingValues(start = 8.dp)) {
        castAndCrewItem.cast.forEach { castItem ->
            item {
                Spacer(modifier = Modifier.padding(start = 8.dp))
                TvShowCastItem(castItem = castItem)
            }
        }
    }
}

@Composable
fun TvShowCastItem(castItem: CastItem) {
    Column(modifier = Modifier.width(100.dp)) {
        Image(
            painter = rememberImagePainter(castItem.profilePath),
            contentDescription = null,
            modifier = Modifier
                .size(width = 100.dp, height = 150.dp)
        )
        Text(text = castItem.name)
    }
}

