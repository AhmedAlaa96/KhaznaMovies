package com.example.khaznamovies.domain.usecases

import com.example.khaznamovies.domain.models.dto.Movie
import com.example.khaznamovies.domain.repositories.KhaznaMoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertMoviesListUseCase @Inject constructor(private val repository: KhaznaMoviesRepository) {
    suspend operator fun invoke(movies: ArrayList<Movie>) {
        withContext(Dispatchers.IO) {
            repository.insertMoviesList(movies)
        }
    }
}