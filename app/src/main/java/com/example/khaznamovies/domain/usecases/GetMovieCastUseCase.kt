package com.example.khaznamovies.domain.usecases

import com.example.khaznamovies.domain.models.dto.MovieCastResponse
import com.example.khaznamovies.domain.models.Status
import com.example.khaznamovies.domain.repositories.KhaznaMoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMovieCastUseCase @Inject constructor(private val repository: KhaznaMoviesRepository) {
    suspend operator fun invoke(movieId: Int?): Status<MovieCastResponse> {
        return withContext(Dispatchers.IO) {
            repository.getMovieCast(movieId)
        }
    }
}