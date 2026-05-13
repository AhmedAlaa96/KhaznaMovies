package com.example.khaznamovies.domain.usecases

import com.example.khaznamovies.data.models.dto.MoviesListResponse
import com.example.khaznamovies.domain.models.Status
import com.example.khaznamovies.domain.repositories.GetMoviesListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTopRatedListUseCase @Inject constructor(private val repository: GetMoviesListRepository) {
    suspend operator fun invoke(): Status<MoviesListResponse> {
        return withContext(Dispatchers.IO) {
            repository.getTopRatedList()
        }
    }
}