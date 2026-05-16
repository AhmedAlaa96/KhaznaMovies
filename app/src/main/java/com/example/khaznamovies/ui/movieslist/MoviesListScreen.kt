package com.example.khaznamovies.ui.movieslist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.khaznamovies.ui.base.LoadingScreen
import com.example.khaznamovies.ui.movieslist.components.MoviesListScreenContent
import com.example.khaznamovies.ui.movieslist.components.WatchListScreenContent

@Composable
fun MoviesListScreen(
    modifier: Modifier = Modifier,
    viewModel: MoviesListViewModel = hiltViewModel(),
    onMovieClicked: (Int?) -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val selectedIndex = remember { mutableIntStateOf(0) }

    Column(modifier = modifier.fillMaxSize()) {
        SecondaryTabRow(
            selectedTabIndex = selectedIndex.intValue,
            containerColor = Color.White,
            contentColor = Color.Black,
            modifier = Modifier.fillMaxWidth()
        ) {
            Tab(
                text = { Text("Movies") },
                selected = selectedIndex.intValue == 0,
                onClick = { selectedIndex.intValue = 0 }
            )
            Tab(
                text = { Text("Watchlist") },
                selected = selectedIndex.intValue == 1,
                onClick = {
                    selectedIndex.intValue = 1
                    viewModel.getWatchlist()
                }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            when (selectedIndex.intValue) {
                0 -> MoviesListScreenContent(
                    topRatedMovies = state.topRatedList,
                    movies = state.moviesList,
                    error = state.error,
                    modifier = Modifier.fillMaxSize(),
                    onMovieClicked = onMovieClicked
                )

                1 -> WatchListScreenContent(
                    movies = state.watchlist,
                    modifier = Modifier.fillMaxSize(),
                    onMovieClicked = onMovieClicked
                )

                else -> Unit
            }
            if (state.isLoading)
                LoadingScreen()
        }
    }

}