package com.example.khaznamovies.domain.usecases

import com.example.khaznamovies.data.models.dto.Movie
import com.example.khaznamovies.domain.repositories.GetMoviesListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateWatchlistUseCase @Inject constructor(private val repository: GetMoviesListRepository) {
    suspend operator fun invoke(movies: ArrayList<Movie>) {
        withContext(Dispatchers.IO) {
            repository.insertMoviesList(movies)
        }
    }
}