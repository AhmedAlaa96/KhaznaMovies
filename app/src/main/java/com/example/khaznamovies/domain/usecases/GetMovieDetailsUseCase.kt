package com.example.khaznamovies.domain.usecases

import com.example.khaznamovies.data.models.dto.MovieDetailsResponse
import com.example.khaznamovies.domain.models.Status
import com.example.khaznamovies.domain.repositories.GetMovieDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repository: GetMovieDetailsRepository) {
    suspend operator fun invoke(movieId: Int?): Status<MovieDetailsResponse> {
        return withContext(Dispatchers.IO) {
            repository.getMovieDetails(movieId)
        }
    }
}