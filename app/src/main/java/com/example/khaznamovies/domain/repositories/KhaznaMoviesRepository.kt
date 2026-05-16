package com.example.khaznamovies.domain.repositories

import com.example.khaznamovies.domain.models.PageModel
import com.example.khaznamovies.domain.models.Status
import com.example.khaznamovies.domain.models.dto.Movie
import com.example.khaznamovies.domain.models.dto.MovieCastResponse
import com.example.khaznamovies.domain.models.dto.MoviesListResponse
import com.example.khaznamovies.domain.models.dto.Watchlist

interface KhaznaMoviesRepository{

    suspend fun getTopRatedList(): Status<MoviesListResponse>
    suspend fun getMoviesList(pageModel: PageModel): Status<MoviesListResponse>
    suspend fun getSimilarMovies(movieId: Int?): Status<MoviesListResponse>
    suspend fun getMovieCast(movieId: Int?): Status<MovieCastResponse>
    suspend fun getLocalMoviesList(): Status<ArrayList<Movie>>
    suspend fun getWatchlist(): Status<ArrayList<Watchlist>>
    suspend fun insertMoviesList(movies: ArrayList<Movie>)
}