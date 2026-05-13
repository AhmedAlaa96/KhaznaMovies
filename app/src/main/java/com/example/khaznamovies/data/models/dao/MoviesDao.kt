package com.example.khaznamovies.data.models.dao

import androidx.room.*
import com.example.khaznamovies.data.models.dto.Movie

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<Movie>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: ArrayList<Movie>)


    @Query("DELETE FROM movies")
    suspend fun clearMoviesList()
}