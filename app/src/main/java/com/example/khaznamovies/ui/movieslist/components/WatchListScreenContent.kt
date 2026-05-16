package com.example.khaznamovies.ui.movieslist.components

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.khaznamovies.ui.base.NetworkImage
import com.example.khaznamovies.ui.movieslist.state.MovieListUiModel

@Composable
fun WatchListScreenContent(
    movies: List<MovieListUiModel>,
    modifier: Modifier = Modifier,
    onMovieClicked: (Int?) -> Unit = {}
) {
    Box(modifier.fillMaxSize()) {
        if (movies.isEmpty()) {
            ErrorScreen(
                error = "Your watchlist is empty",
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(movies) { movie ->
                    WatchlistItem(movie, onItemClicked = { onMovieClicked(it.id) })
                }
            }
        }
    }
}


@Composable
private fun WatchlistItem(movie: MovieListUiModel, onItemClicked: (MovieListUiModel) -> Unit) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        onClick = { onItemClicked(movie) }
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
                        .height(240.dp)
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
        }
    }
}