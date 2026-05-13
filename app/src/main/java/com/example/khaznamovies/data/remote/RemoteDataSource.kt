package com.example.khaznamovies.data.remote

import com.example.khaznamovies.domain.models.PageModel
import com.example.khaznamovies.data.models.dto.MovieDetailsResponse
import com.example.khaznamovies.data.models.dto.MoviesListResponse

interface RemoteDataSource {

    suspend fun getTopRatedList(): MoviesListResponse
    suspend fun getMoviesList(pageModel: PageModel): MoviesListResponse
    suspend fun getMovieDetails(movieId: Int?): MovieDetailsResponse
}