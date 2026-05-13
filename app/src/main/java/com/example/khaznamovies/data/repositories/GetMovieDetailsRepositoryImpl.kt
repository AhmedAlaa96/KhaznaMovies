package com.example.khaznamovies.data.repositories

import com.example.khaznamovies.domain.models.Status
import com.example.khaznamovies.data.models.dto.MovieDetailsResponse
import com.example.khaznamovies.data.remote.RemoteDataSource
import com.example.khaznamovies.data.safeApiCalls
import com.example.khaznamovies.domain.repositories.GetMovieDetailsRepository
import com.example.khaznamovies.utils.connection.IConnectionUtils
import javax.inject.Inject

class GetMovieDetailsRepositoryImpl @Inject constructor(
    private val connectionUtils: IConnectionUtils,
    private val remote: RemoteDataSource
) : GetMovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int?): Status<MovieDetailsResponse> {
        return safeApiCalls(connectionUtils.isConnected) {
            remote.getMovieDetails(movieId)
        }
    }

}