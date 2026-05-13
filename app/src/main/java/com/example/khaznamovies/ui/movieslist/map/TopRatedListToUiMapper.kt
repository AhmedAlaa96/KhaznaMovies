package com.example.khaznamovies.ui.movieslist.map

import com.example.khaznamovies.data.models.dto.Movie
import com.example.khaznamovies.ui.state.MovieUiModel
import com.example.khaznamovies.utils.Utils
import javax.inject.Inject

class TopRatedListToUiMapper @Inject constructor() {

    fun map(moviesList: List<Movie>?): List<MovieUiModel> {
        return moviesList?.map { movie ->
            MovieUiModel(
                id = movie.id,
                title = movie.title,
                releaseDate = movie.releaseDate,
                rating = "${Utils.roundTheNumber(movie.voteAverage ?: 0.0)} ⭐",
                icon = Utils.getImageUrl(movie.posterPath)
            )
        }.orEmpty()
    }
}