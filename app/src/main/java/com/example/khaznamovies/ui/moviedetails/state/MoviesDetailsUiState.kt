package com.example.khaznamovies.ui.moviedetails.state

data class MovieDetailsUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val movieDetailsUiModel: MovieDetailsUiModel? = null,
    val movieCastList: List<MovieCaseUiModel>? = null,
    val similarMoviesList: List<MovieDetailsUiModel>? = null,
    val isMovieInWatchlist: Boolean = false
)

data class MovieDetailsUiModel(
    val id: Int? = null,
    val title: String? = null,
    val releaseDate: String? = null,
    val rating: String? = null,
    val icon: String? = null,
    val genres: String? = null
)

data class MovieCaseUiModel(
    val id: Int? = null,
    val name: String? = null,
    val character: String? = null,
    val icon: String? = null,
    val order: Int? = null
)