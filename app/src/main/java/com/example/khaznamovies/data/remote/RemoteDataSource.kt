package com.example.khaznamovies.data.remote

import com.example.khaznamovies.domain.models.dto.MovieCastResponse
import com.example.khaznamovies.domain.models.PageModel
import com.example.khaznamovies.domain.models.dto.MovieDetailsResponse
import com.example.khaznamovies.domain.models.dto.MoviesListResponse

interface RemoteDataSource {

    suspend fun getTopRatedList(): MoviesListResponse
    suspend fun getMoviesList(pageModel: PageModel): MoviesListResponse
    suspend fun getSimilarMovies(movieId: Int?): MoviesListResponse
    suspend fun getMovieCast(movieId: Int?): MovieCastResponse
    suspend fun getMovieDetails(movieId: Int?): MovieDetailsResponse
}