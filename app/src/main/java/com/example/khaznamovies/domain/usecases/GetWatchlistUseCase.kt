package com.example.khaznamovies.domain.usecases

import com.example.khaznamovies.domain.models.Status
import com.example.khaznamovies.domain.models.dto.Watchlist
import com.example.khaznamovies.domain.repositories.KhaznaMoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetWatchlistUseCase @Inject constructor(private val repository: KhaznaMoviesRepository) {
    suspend operator fun invoke(): Status<ArrayList<Watchlist>> {
        return withContext(Dispatchers.IO) {
            repository.getWatchlist()
        }
    }
}