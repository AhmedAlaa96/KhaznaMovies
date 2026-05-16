package com.example.khaznamovies.data.api

import com.example.khaznamovies.domain.models.dto.MovieCastResponse
import com.example.khaznamovies.utils.Constants
import com.example.khaznamovies.domain.models.dto.MovieDetailsResponse
import com.example.khaznamovies.domain.models.dto.MoviesListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {

    @GET(Constants.URL.GET_TOP_MOVIES)
    suspend fun getTopRatedList(): MoviesListResponse

    @GET(Constants.URL.GET_MOVIES)
    suspend fun getMoviesList(@Query(Constants.QueryParams.PAGE) page: Int): MoviesListResponse

    @GET(Constants.URL.GET_MOVIE_DETAILS)
    suspend fun getMovieDetails(@Path("movieId") movieId: Int?): MovieDetailsResponse

    @GET(Constants.URL.GET_SIMILAR_MOVIES)
    suspend fun getSimilarMovies(@Path("movie_id") movieId: Int?): MoviesListResponse

    @GET(Constants.URL.GET_MOVIE_CAST)
    suspend fun getMovieCast(@Path("movie_id") movieId: Int?): MovieCastResponse

}
