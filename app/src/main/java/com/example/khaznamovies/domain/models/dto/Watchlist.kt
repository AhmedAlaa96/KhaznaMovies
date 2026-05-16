package com.example.khaznamovies.domain.models.dto


import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
@Entity(tableName = "watchlist")
data class Watchlist(
    @PrimaryKey
    val id: Int? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val voteAverage: String? = null
) : Parcelable