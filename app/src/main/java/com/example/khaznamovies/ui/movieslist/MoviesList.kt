package com.example.khaznamovies.ui.movieslist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.khaznamovies.ui.base.LoadingScreen
import com.example.khaznamovies.ui.movieslist.components.MoviesListScreenContent

@Composable
fun MoviesListScreen(
    modifier: Modifier = Modifier,
    viewModel: MoviesListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.loadMovies()
    }

    Box(modifier = modifier.fillMaxSize()) {
        MoviesListScreenContent(
            topRatedMovies = state.topRatedList,
            movies = state.moviesList,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        )
        if (state.isLoading)
            LoadingScreen()
    }

}