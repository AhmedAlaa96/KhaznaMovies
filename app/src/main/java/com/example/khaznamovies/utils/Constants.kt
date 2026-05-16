package com.example.khaznamovies.utils

import com.example.khaznamovies.BuildConfig

object Constants {
    object SharedPreference {
        const val SHARED_PREF_NAME = "my_shared_pref"
    }

    object General {
        const val EMPTY_TEXT = ""
        const val DASH_TEXT = "-"
    }


    object Network {
        const val CONNECT_TIMEOUT = 5L
        const val READ_TIMEOUT = 5L
        const val WRITE_TIMEOUT = 5L
    }

    object URL {
        const val BASE_URL: String = BuildConfig.BASE_NETWORK_URL

        const val GET_TOP_MOVIES = "movie/top_rated?language=en-US?page=1"
        const val GET_MOVIES = "discover/movie?primary_release_year=2024"
        const val GET_MOVIE_DETAILS = "movie/{movieId}?language=en-US"
        const val GET_SIMILAR_MOVIES = "movie/{movie_id}/similar"
        const val GET_MOVIE_CAST = "movie/{movie_id}/credits"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

    }

    object QueryParams {
        const val PAGE = "page"
    }

    object Headers {
        const val AUTHORIZATION = "Authorization"
        const val ACCEPT = "accept"
        const val AUTHORIZATION_VALUE = BuildConfig.AUTH_TOKEN
        const val ACCEPT_VALUE = "application/json"
    }
}