package ru.vitalysizov.moviedb.presentation.home_tab

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.model.domain.tvShows.TvShowItem
import ru.vitalysizov.moviedb.presentation.common.LoadingContent
import ru.vitalysizov.moviedb.presentation.home_tab.viewmodel.HomeTabViewModel

@ExperimentalPagerApi
@Composable
fun HomeTabContent(viewModel: HomeTabViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val loading by viewModel.loading.observeAsState(false)
    val nowPlayingMovies by viewModel.nowPlayingMovies.observeAsState()

    val currentPopularCategory by viewModel.currentMediaTypeCategory.observeAsState()
    val popularCategoryItems by viewModel.mediaTypeCategoryItems.observeAsState()

    val currentTopRatedCategory by viewModel.currentTopRatedCategory.observeAsState()
    val topRatedCategoryItems by viewModel.topRatedCategoryItems.observeAsState()

    LoadingContent(loading) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                MoviesInTheatersList(
                    nowPlayingMovies ?: emptyList()
                ) { viewModel.onMovieClicked(it) }
            }
            item {
                HeaderWithCategories(
                    headerText = stringResource(id = R.string.title_category_popular),
                    selectedIndex = currentPopularCategory?.position ?: 0
                ) {
                    viewModel.onChangeCurrentPopularCategory(it)
                }
            }
            item {
                CategoryList(
                    popularCategoryItems ?: MediaTypeCategoryItems.Movies(emptyList()),
                    onMovieItemClickListener = { viewModel.onMovieClicked(it) },
                    onTvShowItemClickListener = { viewModel.onTvShowClicked(it) })
            }
            item {
                HeaderWithCategories(
                    headerText = stringResource(id = R.string.title_category_top_rated),
                    selectedIndex = currentTopRatedCategory?.position ?: 0,
                ) {
                    viewModel.onChangeCurrentTopRatedCategory(it)
                }
            }
            item {
                CategoryList(
                    topRatedCategoryItems ?: MediaTypeCategoryItems.Movies(emptyList()),
                    onMovieItemClickListener = { viewModel.onMovieClicked(it) },
                    onTvShowItemClickListener = { viewModel.onTvShowClicked(it) }
                )
            }
            item {
                Spacer(modifier = Modifier.padding(bottom = 32.dp))
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun MoviesInTheatersList(movies: List<MovieItem>, onItemClickListener: (MovieItem) -> Unit) {
    HorizontalPager(
        state = rememberPagerState(),
        count = movies.size,
        modifier = Modifier.fillMaxWidth()
    ) { page ->
        MovieInTheaterItem(movies[page], onItemClickListener)
    }
}

@Composable
fun MovieInTheaterItem(movie: MovieItem, onItemClickListener: (MovieItem) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .clickable { onItemClickListener.invoke(movie) }
    ) {
        Image(
            painter = rememberImagePainter(movie.backDropPath),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
        )
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.padding(top = 156.dp, start = 16.dp)
        ) {
            Image(
                painter = rememberImagePainter(movie.posterPath),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(126.dp)
                    .width(84.dp)
            )
            Column(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            ) {
                Text(text = movie.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(text = movie.originalTitle, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

@Composable
fun HeaderWithCategories(headerText: String, selectedIndex: Int, onCategoryChanged: (Int) -> Unit) {
    val cornerRadius = 8.dp

    fun indexChanged(index: Int) {
        onCategoryChanged.invoke(index)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 32.dp)
    ) {
        Text(
            text = headerText,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )

        MediaTypeCategory.values().toList().forEachIndexed { index, item ->
            OutlinedButton(
                modifier = when (index) {
                    0 -> {
                        if (selectedIndex == index) {
                            Modifier
                                .offset(0.dp, 0.dp)
                                .zIndex(1f)
                        } else {
                            Modifier
                                .offset(0.dp, 0.dp)
                                .zIndex(0f)
                        }
                    }
                    else -> {
                        val offset = -1 * index
                        if (selectedIndex == index) {
                            Modifier
                                .offset(offset.dp, 0.dp)
                                .zIndex(1f)
                        } else {
                            Modifier
                                .offset(offset.dp, 0.dp)
                                .zIndex(0f)
                        }
                    }
                },
                onClick = {
                    indexChanged(index)
                },
                shape = when (index) {
                    // left outer button
                    0 -> RoundedCornerShape(
                        topStart = cornerRadius,
                        topEnd = 0.dp,
                        bottomStart = cornerRadius,
                        bottomEnd = 0.dp
                    )
                    // right outer button
                    MediaTypeCategory.values().size - 1 -> RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = cornerRadius,
                        bottomStart = 0.dp,
                        bottomEnd = cornerRadius
                    )
                    // middle button
                    else -> RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                },
                border = BorderStroke(
                    1.dp, if (selectedIndex == index) {
                        MaterialTheme.colors.primary
                    } else {
                        Color.DarkGray.copy(alpha = 0.75f)
                    }
                ),
                colors = if (selectedIndex == index) {
                    // selected colors
                    ButtonDefaults.outlinedButtonColors(
                        backgroundColor = MaterialTheme.colors.primary.copy(
                            alpha = 0.1f
                        ), contentColor = MaterialTheme.colors.primary
                    )
                } else {
                    // not selected colors
                    ButtonDefaults.outlinedButtonColors(
                        backgroundColor = MaterialTheme.colors.surface,
                        contentColor = MaterialTheme.colors.primary
                    )
                },
            ) {
                Text(
                    text = stringResource(id = item.categoryNameId),
                    color = if (selectedIndex == index) {
                        MaterialTheme.colors.primary
                    } else {
                        Color.DarkGray.copy(alpha = 0.9f)
                    },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
fun CategoryList(
    category: MediaTypeCategoryItems,
    onMovieItemClickListener: (MovieItem) -> Unit,
    onTvShowItemClickListener: (TvShowItem) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        when (category) {
            is MediaTypeCategoryItems.Movies -> {
                category.items.forEach {
                    item {
                        MovieListItem(it) { onMovieItemClickListener.invoke(it) }
                    }
                }
            }
            is MediaTypeCategoryItems.TvShow -> {
                category.items.forEach {
                    item {
                        TvShowListItem(it) { onTvShowItemClickListener.invoke(it) }
                    }
                }
            }
        }
    }
}

@Composable
fun MovieListItem(movieItem: MovieItem, onItemClickListener: (MovieItem) -> Unit) {
    HorizontalMovieTvShowItem(
        posterPath = movieItem.posterPath,
        title = movieItem.title,
        modifier = Modifier.clickable { onItemClickListener.invoke(movieItem) }
    )
}

@Composable
fun TvShowListItem(tvShowItem: TvShowItem, onItemClickListener: (TvShowItem) -> Unit) {
    HorizontalMovieTvShowItem(
        posterPath = tvShowItem.posterPath,
        title = tvShowItem.name,
        modifier = Modifier.clickable { onItemClickListener.invoke(tvShowItem) }
    )
}

@Composable
fun HorizontalMovieTvShowItem(posterPath: String, title: String, modifier: Modifier) {
    Column(
        modifier = modifier.width(84.dp)
    ) {
        Image(
            painter = rememberImagePainter(posterPath),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(126.dp)
        )
        Text(
            text = title,
            Modifier.wrapContentWidth(),
            fontSize = 12.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}



