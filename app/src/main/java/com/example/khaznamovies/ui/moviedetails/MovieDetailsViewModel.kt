package com.example.khaznamovies.ui.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khaznamovies.domain.models.Status
import com.example.khaznamovies.domain.usecases.GetMovieCastUseCase
import com.example.khaznamovies.domain.usecases.GetMovieDetailsUseCase
import com.example.khaznamovies.domain.usecases.GetSimilarMoviesUseCase
import com.example.khaznamovies.domain.usecases.InsertIntoWatchlistUseCase
import com.example.khaznamovies.domain.usecases.IsMovieInWatchlistUseCase
import com.example.khaznamovies.domain.usecases.RemoveFromWatchlistUseCase
import com.example.khaznamovies.ui.moviedetails.map.MovieCastToUiMapper
import com.example.khaznamovies.ui.moviedetails.map.MovieDetailsToUiMapper
import com.example.khaznamovies.ui.moviedetails.map.SimilarMoviesListToUiMapper
import com.example.khaznamovies.ui.moviedetails.state.MovieDetailsUiModel
import com.example.khaznamovies.ui.moviedetails.state.MovieDetailsUiState
import com.example.khaznamovies.ui.movieslist.map.toWatchlist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val isMovieInWatchlistUseCase: IsMovieInWatchlistUseCase,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
    private val getMovieCastUseCase: GetMovieCastUseCase,
    private val insertIntoWatchlistUseCase: InsertIntoWatchlistUseCase,
    private val removeFromWatchlistUseCase: RemoveFromWatchlistUseCase,
    private val movieDetailsToUiMapper: MovieDetailsToUiMapper,
    private val similarMoviesListToUiMapper: SimilarMoviesListToUiMapper,
    private val movieCastToUiMapper: MovieCastToUiMapper
) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailsUiState())
    val state = _state.asStateFlow()

    fun loadMovieDetails(movieId: Int?) = viewModelScope.launch {
        updateState { it.copy(isLoading = true) }
        getMoviesDetails(movieId)
        isMovieInWatchlist(movieId)
        getMovieCast(movieId)
        getSimilarMovies(movieId)
    }

    fun changeMovieWatchlistStatus() = viewModelScope.launch {
        if (_state.value.isMovieInWatchlist)
            removeFromWatchlist()
        else
            addToWatchlist()
    }

    private suspend fun addToWatchlist() {
        _state.value.movieDetailsUiModel?.let { movieDetailsUiModel ->
            updateState { it.copy(isLoading = true) }
            when (val insertResult =
                insertIntoWatchlistUseCase(movieDetailsUiModel.toWatchlist())) {
                is Status.Success -> {
                    updateState { it.copy(isMovieInWatchlist = true, isLoading = false) }
                }

                else -> {
                    updateState { it.copy(isLoading = false, error = insertResult.error) }
                }
            }
        }
    }

    private suspend fun removeFromWatchlist() {
        _state.value.movieDetailsUiModel?.let { movieDetailsUiModel ->
            updateState { it.copy(isLoading = true) }
            when (val insertResult =
                insertIntoWatchlistUseCase(movieDetailsUiModel.toWatchlist())) {
                is Status.Success -> {
                    updateState { it.copy(isMovieInWatchlist = false, isLoading = false) }
                }

                else -> {
                    updateState { it.copy(isLoading = false, error = insertResult.error) }
                }
            }
        }
    }

    private suspend fun isMovieInWatchlist(movieId: Int?) {
        when (val isMovieInWatchlist = isMovieInWatchlistUseCase(movieId)) {
            is Status.Success -> {
                updateState {
                    it.copy(
                        isMovieInWatchlist = isMovieInWatchlist.data == true
                    )
                }
            }

            else -> Unit
        }
    }

    private suspend fun getMoviesDetails(movieId: Int?) {
        when (val movieDetails = getMovieDetailsUseCase(movieId)) {
            is Status.Success -> {
                updateState {
                    it.copy(
                        isLoading = false,
                        movieDetailsUiModel = movieDetailsToUiMapper.map(movieDetails.data)
                    )
                }
            }

            else -> {
                updateState {
                    it.copy(
                        isLoading = false,
                        error = movieDetails.error
                    )
                }
            }
        }
    }

    private suspend fun getSimilarMovies(movieId: Int?) {
        when (val similarMovies = getSimilarMoviesUseCase(movieId)) {
            is Status.Success -> {
                updateState {
                    it.copy(
                        similarMoviesList = similarMoviesListToUiMapper
                            .map(similarMovies.data?.movies)
                    )
                }
            }

            else -> {
                updateState {
                    it.copy(
                        error = similarMovies.error
                    )
                }
            }
        }
    }

    private suspend fun getMovieCast(movieId: Int?) {
        when (val movieCast = getMovieCastUseCase(movieId)) {
            is Status.Success -> {
                updateState {
                    it.copy(
                        movieCastList = movieCastToUiMapper
                            .map(movieCast.data?.cast)
                    )
                }
            }

            else -> {
                updateState {
                    it.copy(
                        error = movieCast.error
                    )
                }
            }
        }
    }


    private fun updateState(block: (MovieDetailsUiState) -> MovieDetailsUiState) {
        _state.update(block)
    }
}