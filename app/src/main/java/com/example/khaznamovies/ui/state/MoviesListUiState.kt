package com.example.khaznamovies.ui.state

import com.example.khaznamovies.domain.models.PageModel

data class MoviesListUiState(
    val pageModel: PageModel = PageModel(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val topRatedList: List<MovieUiModel> = emptyList(),
    val moviesList: List<MovieSectionUiState> = emptyList()
)


data class MovieSectionUiState(
    val title: String,
    val movies: List<MovieUiModel>
)

data class MovieUiModel(
    val id: Int? = null,
    val title: String? = null,
    val releaseDate: String? = null,
    val rating: String? = null,
    val icon: String? = null
)