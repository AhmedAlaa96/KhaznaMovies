package com.example.khaznamovies.domain.models.dto


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCastResponse(
    @SerializedName("cast")
    val cast: List<Cast>? = null,
    @SerializedName("crew")
    val crew: List<Crew>? = null,
    @SerializedName("id")
    val id: Int? = null
)