package com.example.khaznamovies.ui.movieslist.map

import com.example.khaznamovies.domain.models.dto.Movie
import com.example.khaznamovies.ui.movieslist.state.MovieSectionUiState
import com.example.khaznamovies.ui.movieslist.state.MovieListUiModel
import com.example.khaznamovies.utils.Utils
import javax.inject.Inject

class MoviesListToUiMapper @Inject constructor() {

    fun map(moviesList: List<Movie>?): List<MovieSectionUiState> {
        return moviesList
            ?.groupBy { it.releaseDate?.take(7) ?: "Unknown" }
            ?.map { (month, movies) ->
                MovieSectionUiState(
                    title = month,
                    movies = movies.map { movie ->
                        MovieListUiModel(
                            id = movie.id,
                            title = movie.title,
                            releaseDate = movie.releaseDate,
                            rating = "${Utils.roundTheNumber(movie.voteAverage ?: 0.0)} ⭐",
                            icon = Utils.getImageUrl(movie.posterPath)
                        )
                    }
                )
            }.orEmpty().sortedByDescending { it.title }
    }
}