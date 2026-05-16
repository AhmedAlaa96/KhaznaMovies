package com.example.khaznamovies.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationModel {

    @Serializable
    data object MoviesList : NavigationModel()

    @Serializable
    data class MovieDetails(val id: Int?) : NavigationModel()

}