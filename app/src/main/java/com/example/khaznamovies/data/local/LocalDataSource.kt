package com.example.khaznamovies.data.local

import com.example.khaznamovies.domain.models.Status
import com.example.khaznamovies.domain.models.dto.Movie
import com.example.khaznamovies.domain.models.dto.Watchlist

interface LocalDataSource {
    suspend fun getAllMovies(): Status<ArrayList<Movie>>
    suspend fun getAllWatchlist(): Status<ArrayList<Watchlist>>
    suspend fun getWatchlistById(movieId: Int?): Status<Watchlist>
    suspend fun insertMovies(movies: ArrayList<Movie>)
    suspend fun insertToWatchlist(movie: Watchlist)
    suspend fun deleteFromWatchlist(movie: Watchlist)
}