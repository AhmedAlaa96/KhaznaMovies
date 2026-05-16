package com.example.khaznamovies.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.khaznamovies.BuildConfig
import com.example.khaznamovies.utils.Constants.URL.IMAGE_BASE_URL
import java.lang.Exception

object Utils {
    fun checkConnection(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            ?: return false
        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) or
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            else -> false
        }
    }

    fun printStackTrace(exception: Exception) {
        if (BuildConfig.DEBUG) {
            exception.printStackTrace()
        }
    }

    fun printStackTrace(throwable: Throwable) {
        if (BuildConfig.DEBUG) {
            throwable.printStackTrace()
        }
    }

    fun roundTheNumber(numInDouble: Double?): String {
        return if (numInDouble == null) Constants.General.DASH_TEXT
        else "%.1f".format((numInDouble))
    }

    fun getImageUrl(path: String?): String? {
        return path?.let {
            "$IMAGE_BASE_URL$it"
        } ?: run { null }
    }
}
