package com.example.khaznamovies.domain.usecases

import com.example.khaznamovies.domain.models.Status
import com.example.khaznamovies.domain.models.dto.Watchlist
import com.example.khaznamovies.domain.repositories.GetMovieDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertIntoWatchlistUseCase @Inject constructor(private val repository: GetMovieDetailsRepository) {
    suspend operator fun invoke(movie: Watchlist): Status<Unit> {
        return withContext(Dispatchers.IO) {
            Status.Success(repository.insertIntoWatchlist(movie))
        }
    }
}