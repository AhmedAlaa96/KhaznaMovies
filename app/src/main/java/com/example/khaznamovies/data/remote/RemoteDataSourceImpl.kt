package com.example.khaznamovies.data.remote

import com.example.khaznamovies.domain.models.PageModel
import com.example.khaznamovies.data.api.ApiInterface
import com.example.khaznamovies.data.models.dto.MovieDetailsResponse
import com.example.khaznamovies.data.models.dto.MoviesListResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val mRetrofitInterface: ApiInterface) : RemoteDataSource {
    override suspend fun getTopRatedList(): MoviesListResponse {
        return mRetrofitInterface.getTopRatedList()
    }

    override suspend fun getMoviesList(pageModel: PageModel): MoviesListResponse {
        return mRetrofitInterface.getMoviesList(pageModel.page)
    }

    override suspend fun getMovieDetails(movieId: Int?): MovieDetailsResponse {
        return mRetrofitInterface.getMovieDetails(movieId)
    }

}