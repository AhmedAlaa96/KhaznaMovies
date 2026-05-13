package com.example.khaznamovies.domain.usecases

import com.example.khaznamovies.data.models.dto.Movie
import com.example.khaznamovies.domain.models.PageModel
import com.example.khaznamovies.domain.models.Status
import com.example.khaznamovies.domain.repositories.GetMoviesListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetLocalMoviesListUseCase @Inject constructor(private val repository: GetMoviesListRepository) {
    suspend operator fun invoke(): Status<ArrayList<Movie>> {
        return withContext(Dispatchers.IO) {
            repository.getLocalMoviesList()
        }
    }
}