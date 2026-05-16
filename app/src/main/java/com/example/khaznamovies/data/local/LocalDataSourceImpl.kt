package com.example.khaznamovies.data.local

import com.example.khaznamovies.domain.models.Status
import com.example.khaznamovies.domain.models.dto.Movie
import com.example.khaznamovies.domain.models.dto.Watchlist
import com.example.khaznamovies.data.room.AppDatabase
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val appDatabase: AppDatabase) : LocalDataSource {
    override suspend fun getAllMovies(): Status<ArrayList<Movie>> {
        val moviesList = appDatabase.moviesDao().getAllMovies()?.toCollection(arrayListOf())
        return if (!moviesList.isNullOrEmpty()) {
            Status.OfflineData(data = moviesList, error = null)
        } else
            Status.NoData(error = "No Data")
    }

    override suspend fun getAllWatchlist(): Status<ArrayList<Watchlist>> {
        val moviesList = appDatabase.watchlistDao().getAllWatchList()?.toCollection(arrayListOf())
        return if (!moviesList.isNullOrEmpty()) {
            Status.OfflineData(data = moviesList, error = null)
        } else
            Status.NoData(error = "No Data")
    }

    override suspend fun getWatchlistById(movieId: Int?): Status<Watchlist> {
        return runCatching {
            val movie = appDatabase.watchlistDao().getWatchListById(movieId)
            if (movie != null) {
                Status.Success(data = movie, error = null)
            } else {
                Status.NoData(error = "Movie not found in watchlist")
            }
        }.getOrElse {
            Status.Error(error = it.message ?: "An error occurred")
        }
    }

    override suspend fun insertMovies(movies: ArrayList<Movie>) {
        deleteAllMovies()
        appDatabase.moviesDao().insertAll(movies)
    }

    override suspend fun insertToWatchlist(movie: Watchlist) {
        appDatabase.watchlistDao().insertToWatchList(movie)
    }

    override suspend fun deleteFromWatchlist(movie: Watchlist) {
        appDatabase.watchlistDao().deleteFromWatchList(movie)
    }

    private suspend fun deleteAllMovies() {
        appDatabase.moviesDao().clearMoviesList()
    }
}