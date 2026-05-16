package com.example.khaznamovies.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.khaznamovies.ui.moviedetails.MovieDetailsScreen
import com.example.khaznamovies.ui.movieslist.MoviesListScreen

@Composable
fun MoviesNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationModel.MoviesList,
        modifier
    ) {
        composable<NavigationModel.MoviesList> {
            MoviesListScreen(onMovieClicked = { movieId ->
                navController.navigate(NavigationModel.MovieDetails(movieId))
            })
        }
        composable<NavigationModel.MovieDetails> { backStackEntry ->
            val movieDetails = backStackEntry.toRoute<NavigationModel.MovieDetails>()
            MovieDetailsScreen(
                movieId = movieDetails.id,
                onSimilarMovieClicked = { movieId ->
                    navController.navigate(NavigationModel.MovieDetails(movieId)) {
                        popUpTo(NavigationModel.MoviesList)
                    }
                },
                onBackClicked = {
                    navController.navigateUp()
                }
            )
        }
    }
}

