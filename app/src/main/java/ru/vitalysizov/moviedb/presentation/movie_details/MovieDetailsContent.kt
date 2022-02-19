package ru.vitalysizov.moviedb.presentation.movie_details

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.model.domain.images.ImageItem
import ru.vitalysizov.moviedb.model.domain.movies.MovieDetailsItem
import ru.vitalysizov.moviedb.presentation.common.*
import ru.vitalysizov.moviedb.presentation.movie_details.viewmodel.MovieDetailsViewModel

@ExperimentalCoilApi
@ExperimentalPagerApi
@Preview(showSystemUi = true)
@Composable
fun MovieDetailsContent(viewModel: MovieDetailsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    val loading by viewModel.loading.observeAsState(false)
    val movieDetailsContent by viewModel.movieDetailsContent.observeAsState()

//    TopAppBar(title = { movieDetailsContent?.movieDetails?.title.orEmpty() })

    LoadingContent(loading) {
        movieDetailsContent?.let { content ->
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item { ImagePager(images = content.movieImages.backdrops) }
                item { TitleMovieDetailsContent(content.movieDetails) }
                item { Spacer(modifier = Modifier.padding(top = 8.dp)) }
                item { DefaultDivider() }
                item { Spacer(modifier = Modifier.padding(top = 16.dp)) }
                item {
                    PosterAndOverview(
                        content.movieDetails.posterPath,
                        content.movieDetails.overview,
                        onOverviewDetailsClicked = {
                            //TODO navigate to overview details
                        })
                }
                item { Spacer(modifier = Modifier.padding(top = 16.dp)) }
                item { DefaultDivider() }
                item {
                    CastHorizontalList(content.castAndCrew.cast, onAllCastClicked = {
                        //TODO navigate to all cast
                    })
                }
                item { Spacer(modifier = Modifier.padding(top = 16.dp)) }
                item { CrewDirectors(items = content.castAndCrew.crew, { }) }
                item { Spacer(modifier = Modifier.padding(top = 8.dp)) }
                item { CrewWriters(items = content.castAndCrew.crew) }
                item { Spacer(modifier = Modifier.padding(top = 16.dp)) }
                item {
                    Text(
                        text = stringResource(id = R.string.all_cast_and_crew),
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                            .fillMaxWidth()
                            .clickable { },
                        color = colorResource(id = R.color.colorPrimary)
                    )
                }
                item { Spacer(modifier = Modifier.padding(top = 56.dp)) }
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun ImagePager(images: List<ImageItem>) {
    HorizontalPager(
        state = rememberPagerState(),
        modifier = Modifier.fillMaxWidth(),
        count = images.size
    ) { page ->
        Image(
            painter = rememberImagePainter(images[page].filePath),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
        )
    }
}

@Composable
fun TitleMovieDetailsContent(movieDetails: MovieDetailsItem?) {
    val resources = LocalContext.current.resources

    movieDetails?.let {
        val title = movieDetails.title
        val originalTitleAndYear = movieDetails.getOriginalTitleAndYear(resources)
        val productionCountries = movieDetails.getProductionCountries()
        val runTime = movieDetails.getRunTime(resources)
        val genres = movieDetails.genres.joinToString { it.name }

        Column(modifier = Modifier.padding(16.dp)) {
            if (title.isNotEmpty()) {
                Text(
                    text = movieDetails.title,
                    fontSize = 22.sp,
                    color = Color.Black
                )
            }
            if (originalTitleAndYear.isNotEmpty()) {
                Text(
                    text = originalTitleAndYear,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            if (productionCountries.isNotEmpty()) {
                Text(
                    text = productionCountries,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            if (runTime.isNotEmpty()) {
                Text(
                    text = runTime,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            if (genres.isNotEmpty()) {
                Text(
                    text = genres,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun MovieDetailsAppBar(
    title: String?,
    showAppBarBackground: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val backgroundColor by animateColorAsState(
        targetValue = when {
            showAppBarBackground -> MaterialTheme.colors.surface
            else -> Color.Transparent
        },
        animationSpec = spring(),
    )

    val elevation by animateDpAsState(
        targetValue = when {
            showAppBarBackground -> 4.dp
            else -> 0.dp
        },
        animationSpec = spring(),
    )

    TopAppBar(
        title = {
            Crossfade(showAppBarBackground && title != null) { show ->
                if (show) Text(text = title!!)
            }
        },

        navigationIcon = {
            IconButton(
                onClick = navigateUp
//                modifier = Modifier.iconButtonBackgroundScrim(enabled = !showAppBarBackground),
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.cd_navigate_up),
                )
            }
        },
        actions = {
//            if (isRefreshing) {
//                AutoSizedCircularProgressIndicator(
//                    modifier = Modifier
//                        .aspectRatio(1f)
//                        .fillMaxHeight()
//                        .padding(16.dp)
//                )
//            } else {
//                IconButton(
//                    onClick = refresh,
//                    modifier = Modifier.iconButtonBackgroundScrim(enabled = !showAppBarBackground),
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Refresh,
//                        contentDescription = stringResource(R.string.cd_refresh)
//                    )
//                }
//            }
        },
        elevation = elevation,
        backgroundColor = backgroundColor,
        modifier = modifier.padding(
            rememberInsetsPaddingValues(
                LocalWindowInsets.current.systemBars,
                applyBottom = false
            )
        )
    )

}