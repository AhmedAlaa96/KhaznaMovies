package com.example.khaznamovies.ui.moviedetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.khaznamovies.ui.base.LoadingScreen
import com.example.khaznamovies.ui.base.NetworkImage
import com.example.khaznamovies.ui.moviedetails.state.MovieCaseUiModel
import com.example.khaznamovies.ui.moviedetails.state.MovieDetailsUiModel
import com.example.khaznamovies.ui.moviedetails.state.MovieDetailsUiState
import com.example.khaznamovies.ui.movieslist.components.ErrorScreen

@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    movieId: Int?,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    onSimilarMovieClicked: (movieId: Int?) -> Unit = {},
    onBackClicked: () -> Unit = {},
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.loadMovieDetails(movieId)
    }
    state.error?.let {
        Box(Modifier.fillMaxSize()) {
            ErrorScreen(
                error = it,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } ?: run {
        MovieDetailsContent(
            state = state,
            modifier = modifier,
            changeMovieWatchlistStatus = viewModel::changeMovieWatchlistStatus,
            onBackClicked = onBackClicked,
            onSimilarMovieClicked = onSimilarMovieClicked
        )
    }
}

@Composable
private fun MovieDetailsContent(
    state: MovieDetailsUiState,
    modifier: Modifier = Modifier,
    changeMovieWatchlistStatus: () -> Unit,
    onBackClicked: () -> Unit,
    onSimilarMovieClicked: (Int?) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        TopBar(
            isMovieInWatchlist = state.isMovieInWatchlist,
            onFavoriteClicked = changeMovieWatchlistStatus,
            onBackClicked = onBackClicked
        )
        Box(modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                state.movieDetailsUiModel?.let {
                    MovieItem(movie = it, isDetails = true)
                }
                state.movieCastList?.let {
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Cast",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.height(16.dp))
                    LazyRow {
                        items(it) { cast ->
                            CastItem(cast)
                        }
                    }
                }
                state.similarMoviesList?.let {
                    if (it.isNotEmpty()) {
                        Spacer(Modifier.height(16.dp))
                        Text(
                            text = "Similar Movies",
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color = Color.Black,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.height(16.dp))
                    }
                    LazyRow {
                        items(it) { movie ->
                            MovieItem(movie, onSimilarMovieClicked = onSimilarMovieClicked)
                        }
                    }
                }
            }
            if (state.isLoading)
                LoadingScreen()
        }
    }
}

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    isMovieInWatchlist: Boolean = false,
    onBackClicked: () -> Unit = {},
    onFavoriteClicked: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        contentAlignment = Alignment.Center
    ) {
        BackButton(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 16.dp),
            onBackClicked = onBackClicked
        )
        Text(
            text = "Movie Details",
            modifier = Modifier.padding(horizontal = 16.dp),
            color = Color.Black,
            style = MaterialTheme.typography.titleMedium
        )
        Icon(
            imageVector = if (isMovieInWatchlist) Icons.Default.Favorite else Icons.Outlined.Favorite,
            contentDescription = "Favorite",
            tint = if (isMovieInWatchlist) Color.Red else Color.Gray,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
                .clickable { onFavoriteClicked() },
        )
    }
}

@Composable
fun BackButton(modifier: Modifier, onBackClicked: () -> Unit) {
    Icon(
        imageVector = Icons.AutoMirrored.Default.ArrowBack,
        contentDescription = "Back",
        tint = Color.Black,
        modifier = modifier.clickable { onBackClicked() }
    )
}


@Composable
private fun MovieItem(
    movie: MovieDetailsUiModel,
    modifier: Modifier = Modifier,
    isDetails: Boolean = false,
    onSimilarMovieClicked: (movieId: Int?) -> Unit = {}
) {
    Card(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        onClick = { onSimilarMovieClicked(movie.id) }
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(min = 120.dp, max = 200.dp)
        ) {
            movie.icon?.let {
                NetworkImage(
                    imageUrl = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(if (isDetails) 360.dp else 240.dp)
                        .clip(RoundedCornerShape(8.dp, 8.dp, 0.dp, 0.dp)),
                    contentDescription = movie.title.orEmpty()
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = movie.title.orEmpty(),
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                overflow = TextOverflow.Ellipsis,
                minLines = 1,
                maxLines = 1
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = movie.releaseDate.orEmpty(),
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                minLines = 1,
                maxLines = 1,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = movie.rating.orEmpty(),
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color.Black,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                minLines = 2,
                maxLines = 2,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = movie.genres.orEmpty(),
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color.Black,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                minLines = 2,
                maxLines = 2,
            )
        }
    }
}

@Composable
private fun CastItem(
    cast: MovieCaseUiModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(min = 120.dp, max = 200.dp)
        ) {
            cast.icon?.let {
                NetworkImage(
                    imageUrl = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .clip(RoundedCornerShape(8.dp, 8.dp, 0.dp, 0.dp)),
                    contentDescription = cast.name.orEmpty()
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = cast.name.orEmpty(),
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                overflow = TextOverflow.Ellipsis,
                minLines = 1,
                maxLines = 1
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = cast.character.orEmpty(),
                modifier = Modifier.padding(horizontal = 14.dp),
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                minLines = 1,
                maxLines = 1,
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}