package com.example.khaznamovies.data.repositories

import com.example.khaznamovies.data.local.LocalDataSource
import com.example.khaznamovies.domain.models.PageModel
import com.example.khaznamovies.domain.models.Status
import com.example.khaznamovies.domain.models.dto.Movie
import com.example.khaznamovies.domain.models.dto.MovieCastResponse
import com.example.khaznamovies.domain.models.dto.MoviesListResponse
import com.example.khaznamovies.data.remote.RemoteDataSource
import com.example.khaznamovies.data.safeApiCalls
import com.example.khaznamovies.domain.models.dto.Watchlist
import com.example.khaznamovies.domain.repositories.KhaznaMoviesRepository
import com.example.khaznamovies.utils.connection.IConnectionUtils
import javax.inject.Inject

class KhaznaMoviesRepositoryImpl @Inject constructor(
    private val connectionUtils: IConnectionUtils,
    private val remoteSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : KhaznaMoviesRepository {
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

    override suspend fun getSimilarMovies(movieId: Int?): Status<MoviesListResponse> {
        return safeApiCalls(connectionUtils.isConnected) {
            remoteSource.getSimilarMovies(movieId)
        }
    }

    override suspend fun getMovieCast(movieId: Int?): Status<MovieCastResponse> {
        return safeApiCalls(connectionUtils.isConnected) {
            remoteSource.getMovieCast(movieId)
        }
    }

    override suspend fun getLocalMoviesList(): Status<ArrayList<Movie>> {
        return localDataSource.getAllMovies()
    }

    override suspend fun getWatchlist(): Status<ArrayList<Watchlist>> {
        return localDataSource.getAllWatchlist()
    }

    override suspend fun insertMoviesList(movies: ArrayList<Movie>) {
        localDataSource.insertMovies(movies)
    }

}