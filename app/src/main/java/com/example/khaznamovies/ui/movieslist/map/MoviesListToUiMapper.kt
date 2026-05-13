package com.example.khaznamovies.ui.movieslist.map

import com.example.khaznamovies.data.models.dto.Movie
import com.example.khaznamovies.ui.state.MovieSectionUiState
import com.example.khaznamovies.ui.state.MovieUiModel
import com.example.khaznamovies.utils.Utils
import javax.inject.Inject

class MoviesListToUiMapper @Inject constructor() {

    fun map(moviesList: List<Movie>?): List<MovieSectionUiState> {
        return moviesList
        ?.groupBy { it.releaseDate?.take(4) ?: "Unknown" }
            ?.map { (year, movies) ->
                MovieSectionUiState(
                    title = year,
                    movies = movies.map { movie ->
                        MovieUiModel(
                            id = movie.id,
                            title = movie.title,
                            releaseDate = movie.releaseDate,
                            rating = "${Utils.roundTheNumber(movie.voteAverage ?: 0.0)} ⭐",
                            icon = Utils.getImageUrl(movie.posterPath)
                        )
                    }
                )
            }.orEmpty()
    }
}