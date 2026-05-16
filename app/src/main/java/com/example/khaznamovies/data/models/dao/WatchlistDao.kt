package com.example.khaznamovies.data.models.dao

import androidx.room.*
import com.example.khaznamovies.domain.models.dto.Watchlist

@Dao
interface WatchlistDao {
    @Query("SELECT * FROM watchlist")
    suspend fun getAllWatchList(): List<Watchlist>?
    @Query("SELECT * FROM watchlist WHERE id = :movieId")
    suspend fun getWatchListById(movieId: Int?): Watchlist?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToWatchList(movie: Watchlist)

    @Delete
    suspend fun deleteFromWatchList(movie: Watchlist)
}