package com.example.khaznamovies.domain.repositories

import com.example.khaznamovies.domain.models.Status
import com.example.khaznamovies.data.models.dto.MovieDetailsResponse

interface GetMovieDetailsRepository  {
    suspend fun getMovieDetails(movieId: Int?): Status<MovieDetailsResponse>
}