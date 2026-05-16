package com.example.khaznamovies.ui.moviedetails.map

import com.example.khaznamovies.domain.models.dto.MovieDetailsResponse
import com.example.khaznamovies.ui.moviedetails.state.MovieDetailsUiModel
import com.example.khaznamovies.utils.Utils
import javax.inject.Inject

class MovieDetailsToUiMapper @Inject constructor() {

    fun map(movie: MovieDetailsResponse?): MovieDetailsUiModel {
        return MovieDetailsUiModel(
            id = movie?.id,
            title = movie?.title,
            releaseDate = movie?.releaseDate,
            rating = "${Utils.roundTheNumber(movie?.voteAverage ?: 0.0)} ⭐",
            icon = Utils.getImageUrl(movie?.posterPath),
            genres = movie?.genres?.joinToString(", ") { it.name.orEmpty() }
        )
    }
}