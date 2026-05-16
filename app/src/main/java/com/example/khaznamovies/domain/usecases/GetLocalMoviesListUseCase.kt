package com.example.khaznamovies.domain.usecases

import com.example.khaznamovies.domain.models.dto.Movie
import com.example.khaznamovies.domain.models.Status
import com.example.khaznamovies.domain.repositories.KhaznaMoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetLocalMoviesListUseCase @Inject constructor(private val repository: KhaznaMoviesRepository) {
    suspend operator fun invoke(): Status<ArrayList<Movie>> {
        return withContext(Dispatchers.IO) {
            repository.getLocalMoviesList()
        }
    }
}