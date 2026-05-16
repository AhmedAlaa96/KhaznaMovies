package com.example.khaznamovies.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.khaznamovies.data.models.dao.MoviesDao
import com.example.khaznamovies.domain.models.dto.Movie
import com.example.khaznamovies.data.models.dao.GenresConverter
import com.example.khaznamovies.data.models.dao.WatchlistDao
import com.example.khaznamovies.domain.models.dto.Watchlist

@TypeConverters(GenresConverter::class)
@Database(entities = [Movie::class, Watchlist::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun watchlistDao(): WatchlistDao
}