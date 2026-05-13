package com.example.khaznamovies.data.local

import com.example.khaznamovies.domain.models.Status
import com.example.khaznamovies.data.models.dto.Movie
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

    override suspend fun insertMovies(movies: ArrayList<Movie>) {
        deleteAllMovies()
        appDatabase.moviesDao().insertAll(movies)
    }

    private suspend fun deleteAllMovies() {
        appDatabase.moviesDao().clearMoviesList()
    }
}