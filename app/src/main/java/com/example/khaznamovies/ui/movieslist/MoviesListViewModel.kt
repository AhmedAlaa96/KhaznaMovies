package com.example.khaznamovies.ui.movieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khaznamovies.domain.models.dto.Movie
import com.example.khaznamovies.domain.models.dto.MoviesListResponse
import com.example.khaznamovies.domain.models.Status
import com.example.khaznamovies.domain.usecases.GetLocalMoviesListUseCase
import com.example.khaznamovies.domain.usecases.GetMoviesListUseCase
import com.example.khaznamovies.domain.usecases.GetTopRatedListUseCase
import com.example.khaznamovies.domain.usecases.GetWatchlistUseCase
import com.example.khaznamovies.domain.usecases.InsertMoviesListUseCase
import com.example.khaznamovies.ui.movieslist.map.MoviesListToUiMapper
import com.example.khaznamovies.ui.movieslist.map.TopRatedListToUiMapper
import com.example.khaznamovies.ui.movieslist.map.toMovieListUiModel
import com.example.khaznamovies.ui.movieslist.state.MoviesListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.orEmpty

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val getTopRatedListUseCase: GetTopRatedListUseCase,
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val getLocalMoviesListUseCase: GetLocalMoviesListUseCase,
    private val insertMoviesListUseCase: InsertMoviesListUseCase,
    private val getWatchlistUseCase: GetWatchlistUseCase,
    private val topRatedListToUiMapper: TopRatedListToUiMapper,
    private val moviesListToUiMapper: MoviesListToUiMapper,
) : ViewModel() {

    private val _state = MutableStateFlow(MoviesListUiState())
    val state = _state.asStateFlow()

    init {
        loadMovies()
    }

    private fun loadMovies() = viewModelScope.launch {
        val topRated = async { getTopRatedListUseCase() }
        val moviesList = async { getMoviesListUseCase(_state.value.pageModel) }
        val localMoviesList = async { getLocalMoviesListUseCase() }
        getTopRatedList(topRated)
        getLocalMoviesList(localMoviesList, moviesList)
        getWatchlist()
    }

    fun getWatchlist() = viewModelScope.launch {
        when (val watchlistResult = getWatchlistUseCase()) {
            is Status.OfflineData -> {
                updateState {
                    it.copy(
                        watchlist = watchlistResult.data?.map { it.toMovieListUiModel() }.orEmpty(),
                    )
                }
            }

            else -> {
                updateState {
                    it.copy(
                        watchlist = emptyList(),
                    )
                }
            }
        }
    }

    private suspend fun getLocalMoviesList(
        localMoviesList: Deferred<Status<ArrayList<Movie>>>,
        moviesList: Deferred<Status<MoviesListResponse>>
    ) {
        when (val localMoviesListResult = localMoviesList.await()) {
            is Status.OfflineData -> {
                updateState {
                    it.copy(
                        moviesList = moviesListToUiMapper.map(localMoviesListResult.data),
                    )
                }
                getMoviesList(moviesList, needLoading = false)
            }

            else -> getMoviesList(moviesList)
        }
    }

    private suspend fun getMoviesList(
        moviesList: Deferred<Status<MoviesListResponse>>,
        needLoading: Boolean = true
    ) {
        updateState {
            it.copy(
                isLoading = needLoading
            )
        }
        when (val moviesListResult = moviesList.await()) {
            is Status.Success -> {
                updateState {
                    it.copy(
                        moviesList = moviesListToUiMapper.map(moviesListResult.data?.movies),
                        isLoading = false
                    )
                }
                insertMoviesListUseCase(moviesListResult.data?.movies ?: arrayListOf())
            }

            else -> {
                updateState {
                    it.copy(
                        moviesList = emptyList(),
                        isLoading = false
                    )
                }
            }
        }
    }

    private suspend fun getTopRatedList(topRated: Deferred<Status<MoviesListResponse>>) {
        updateState { it.copy(isLoading = true) }
        when (val topRatedResult = topRated.await()) {
            is Status.Success -> {
                updateState {
                    it.copy(
                        topRatedList = topRatedListToUiMapper.map(topRatedResult.data?.movies),
                        isLoading = false
                    )
                }
            }

            else -> {
                updateState {
                    it.copy(
                        topRatedList = emptyList(),
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun updateState(block: (MoviesListUiState) -> MoviesListUiState) {
        _state.update(block)
    }
}