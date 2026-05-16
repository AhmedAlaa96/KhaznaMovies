package com.example.khaznamovies.domain.usecases

import com.example.khaznamovies.domain.models.PageModel
import com.example.khaznamovies.domain.models.Status
import com.example.khaznamovies.domain.models.dto.MoviesListResponse
import com.example.khaznamovies.domain.repositories.KhaznaMoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMoviesListUseCase @Inject constructor(private val repository: KhaznaMoviesRepository) {
    suspend operator fun invoke(pageModel: PageModel): Status<MoviesListResponse> {
        return withContext(Dispatchers.IO) {
            repository.getMoviesList(pageModel)
        }
    }
}