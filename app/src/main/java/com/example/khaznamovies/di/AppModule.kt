package com.example.khaznamovies.di

import android.content.Context
import androidx.room.Room
import com.example.khaznamovies.data.api.ApiInterface
import com.example.khaznamovies.data.models.dao.GenresConverter
import com.example.khaznamovies.data.network.HeaderInterceptor
import com.example.khaznamovies.data.room.AppDatabase
import com.example.khaznamovies.utils.Constants
import com.example.khaznamovies.utils.connection.ConnectionUtils
import com.example.khaznamovies.utils.connection.IConnectionUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.example.khaznamovies.BuildConfig

@Module
@InstallIn(SingletonComponent::class)
internal class AppModule {

    @Provides
    @Singleton
    fun provideOkHttp(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: HeaderInterceptor
    ): OkHttpClient {
        val okhttp = OkHttpClient.Builder()
            .connectTimeout(Constants.Network.CONNECT_TIMEOUT, TimeUnit.MINUTES)
            .readTimeout(Constants.Network.READ_TIMEOUT, TimeUnit.MINUTES)
            .writeTimeout(Constants.Network.WRITE_TIMEOUT, TimeUnit.MINUTES)

        okhttp.addInterceptor(headerInterceptor)

        if (BuildConfig.DEBUG)
            okhttp.addInterceptor(httpLoggingInterceptor)
        return okhttp.build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): HeaderInterceptor = HeaderInterceptor()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URL.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "khazna_movies_db"
        ).addTypeConverter(GenresConverter())
            .build()
    }

    @Provides
    @Singleton
    fun provideConnectivity(@ApplicationContext context: Context): IConnectionUtils {
        return ConnectionUtils(context)
    }

}