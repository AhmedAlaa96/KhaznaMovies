package com.example.khaznamovies.ui.moviedetails.map

import com.example.khaznamovies.domain.models.dto.Movie
import com.example.khaznamovies.ui.moviedetails.state.MovieDetailsUiModel
import com.example.khaznamovies.utils.Utils
import javax.inject.Inject

class SimilarMoviesListToUiMapper @Inject constructor() {

    fun map(moviesList: List<Movie>?): List<MovieDetailsUiModel> {
        return moviesList?.map { movie ->
            MovieDetailsUiModel(
                id = movie.id,
                title = movie.title,
                releaseDate = movie.releaseDate,
                rating = "${Utils.roundTheNumber(movie.voteAverage ?: 0.0)} ⭐",
                icon = Utils.getImageUrl(movie.posterPath)
            )
        }.orEmpty()
    }
}