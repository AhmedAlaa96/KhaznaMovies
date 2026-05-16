package com.example.khaznamovies.ui.movieslist.state

import com.example.khaznamovies.domain.models.PageModel

data class MoviesListUiState(
    val pageModel: PageModel = PageModel(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val topRatedList: List<MovieListUiModel> = emptyList(),
    val moviesList: List<MovieSectionUiState> = emptyList(),
    val watchlist: List<MovieListUiModel> = emptyList()
)


data class MovieSectionUiState(
    val title: String,
    val movies: List<MovieListUiModel>
)

data class MovieListUiModel(
    val id: Int? = null,
    val title: String? = null,
    val releaseDate: String? = null,
    val rating: String? = null,
    val icon: String? = null
)