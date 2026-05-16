package com.example.khaznamovies.data.repositories

import com.example.khaznamovies.data.local.LocalDataSource
import com.example.khaznamovies.domain.models.Status
import com.example.khaznamovies.domain.models.dto.MovieDetailsResponse
import com.example.khaznamovies.data.remote.RemoteDataSource
import com.example.khaznamovies.data.safeApiCalls
import com.example.khaznamovies.domain.models.dto.Watchlist
import com.example.khaznamovies.domain.repositories.GetMovieDetailsRepository
import com.example.khaznamovies.utils.connection.IConnectionUtils
import javax.inject.Inject

class GetMovieDetailsRepositoryImpl @Inject constructor(
    private val connectionUtils: IConnectionUtils,
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
) : GetMovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int?): Status<MovieDetailsResponse> {
        return safeApiCalls(connectionUtils.isConnected) {
            remote.getMovieDetails(movieId)
        }
    }

    override suspend fun insertIntoWatchlist(movie: Watchlist) {
        local.insertToWatchlist(movie)
    }

    override suspend fun removeFromWatchlist(movie: Watchlist) {
        local.deleteFromWatchlist(movie)
    }

    override suspend fun isMovieInWatchlist(movieId: Int?): Status<Boolean> {
        local.getWatchlistById(movieId).let {
            return when (it) {
                is Status.Success -> Status.Success(true)
                else -> Status.Success(false)
            }
        }
    }

}