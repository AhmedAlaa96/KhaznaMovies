package com.example.khaznamovies.ui.movieslist.map

import com.example.khaznamovies.domain.models.dto.Watchlist
import com.example.khaznamovies.ui.moviedetails.state.MovieDetailsUiModel
import com.example.khaznamovies.ui.movieslist.state.MovieListUiModel

fun MovieDetailsUiModel.toWatchlist() = Watchlist(
    id = id,
    posterPath = icon,
    releaseDate = releaseDate,
    title = title,
    voteAverage = rating
)

fun Watchlist.toMovieListUiModel() = MovieListUiModel(
    id = id,
    title = title,
    releaseDate = releaseDate,
    rating = voteAverage,
    icon = posterPath
)

