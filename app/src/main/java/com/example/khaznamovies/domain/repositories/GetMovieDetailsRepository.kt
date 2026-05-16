package com.example.khaznamovies.domain.repositories

import com.example.khaznamovies.domain.models.Status
import com.example.khaznamovies.domain.models.dto.MovieDetailsResponse
import com.example.khaznamovies.domain.models.dto.Watchlist

interface GetMovieDetailsRepository  {
    suspend fun getMovieDetails(movieId: Int?): Status<MovieDetailsResponse>

    suspend fun insertIntoWatchlist(movie: Watchlist)

    suspend fun removeFromWatchlist(movie: Watchlist)

    suspend fun isMovieInWatchlist(movieId: Int?): Status<Boolean>
}