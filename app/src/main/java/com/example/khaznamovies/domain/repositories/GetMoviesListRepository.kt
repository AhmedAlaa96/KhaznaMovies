package com.example.khaznamovies.domain.repositories

import com.example.khaznamovies.domain.models.PageModel
import com.example.khaznamovies.domain.models.Status
import com.example.khaznamovies.data.models.dto.Movie
import com.example.khaznamovies.data.models.dto.MoviesListResponse

interface GetMoviesListRepository{

    suspend fun getTopRatedList(): Status<MoviesListResponse>
    suspend fun getMoviesList(pageModel: PageModel): Status<MoviesListResponse>
    suspend fun getLocalMoviesList(): Status<ArrayList<Movie>>
    suspend fun insertMoviesList(movies: ArrayList<Movie>)
}