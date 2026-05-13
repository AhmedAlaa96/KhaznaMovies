package com.example.khaznamovies.data.local

import com.example.khaznamovies.domain.models.Status
import com.example.khaznamovies.data.models.dto.Movie

interface LocalDataSource {
    suspend fun getAllMovies(): Status<ArrayList<Movie>>
    suspend fun insertMovies(movies: ArrayList<Movie>)

}