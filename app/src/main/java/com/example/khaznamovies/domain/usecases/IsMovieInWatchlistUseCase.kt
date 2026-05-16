package com.example.khaznamovies.domain.usecases

import com.example.khaznamovies.domain.models.Status
import com.example.khaznamovies.domain.repositories.GetMovieDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IsMovieInWatchlistUseCase @Inject constructor(private val repository: GetMovieDetailsRepository) {
    suspend operator fun invoke(movieId: Int?): Status<Boolean> {
        return withContext(Dispatchers.IO) {
            repository.isMovieInWatchlist(movieId)
        }
    }
}