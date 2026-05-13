package com.example.khaznamovies.data.repositories

import com.example.khaznamovies.data.local.LocalDataSource
import com.example.khaznamovies.domain.models.PageModel
import com.example.khaznamovies.domain.models.Status
import com.example.khaznamovies.data.models.dto.Movie
import com.example.khaznamovies.data.models.dto.MoviesListResponse
import com.example.khaznamovies.data.remote.RemoteDataSource
import com.example.khaznamovies.data.safeApiCalls
import com.example.khaznamovies.domain.repositories.GetMoviesListRepository
import com.example.khaznamovies.utils.connection.IConnectionUtils
import javax.inject.Inject

class GetMoviesListRepositoryImpl @Inject constructor(
    private val connectionUtils: IConnectionUtils,
    private val remoteSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : GetMoviesListRepository {
    override suspend fun getTopRatedList(): Status<MoviesListResponse> {
        return safeApiCalls(connectionUtils.isConnected) {
            remoteSource.getTopRatedList()
        }
    }

    override suspend fun getMoviesList(pageModel: PageModel): Status<MoviesListResponse> {
        return safeApiCalls(connectionUtils.isConnected) {
            remoteSource.getMoviesList(pageModel)
        }
    }

    override suspend fun getLocalMoviesList(): Status<ArrayList<Movie>> {
        return localDataSource.getAllMovies()
    }

    override suspend fun insertMoviesList(movies: ArrayList<Movie>) {
        localDataSource.insertMovies(movies)
    }

}