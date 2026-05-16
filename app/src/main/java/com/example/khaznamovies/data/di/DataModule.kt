package com.example.khaznamovies.data.di

import com.example.khaznamovies.data.repositories.KhaznaMoviesRepositoryImpl
import com.example.khaznamovies.data.local.LocalDataSource
import com.example.khaznamovies.data.local.LocalDataSourceImpl
import com.example.khaznamovies.data.remote.RemoteDataSource
import com.example.khaznamovies.data.remote.RemoteDataSourceImpl
import com.example.khaznamovies.data.repositories.GetMovieDetailsRepositoryImpl
import com.example.khaznamovies.domain.repositories.GetMovieDetailsRepository
import com.example.khaznamovies.domain.repositories.KhaznaMoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DataModule {
    @Binds
    fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    @Binds
    fun bindGetMoviesListRepository(getMoviesListRepositoryImpl: KhaznaMoviesRepositoryImpl): KhaznaMoviesRepository

    @Binds
    fun bindGetMovieDetailsRepository(getMovieDetailsRepositoryImpl: GetMovieDetailsRepositoryImpl): GetMovieDetailsRepository
}