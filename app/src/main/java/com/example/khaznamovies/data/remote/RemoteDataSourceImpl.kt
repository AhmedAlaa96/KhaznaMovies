package com.example.khaznamovies.data.remote

import com.example.khaznamovies.domain.models.PageModel
import com.example.khaznamovies.data.api.ApiInterface
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: ApiInterface) : RemoteDataSource {
    override suspend fun getTopRatedList() =
        api.getTopRatedList()

    override suspend fun getMoviesList(pageModel: PageModel) =
        api.getMoviesList(pageModel.page)

    override suspend fun getSimilarMovies(movieId: Int?) =
        api.getSimilarMovies(movieId)

    override suspend fun getMovieCast(movieId: Int?) =
        api.getMovieCast(movieId)

    override suspend fun getMovieDetails(movieId: Int?) =
        api.getMovieDetails(movieId)
}