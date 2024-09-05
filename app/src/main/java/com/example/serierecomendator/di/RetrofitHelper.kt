package com.example.serierecomendator.di

import com.example.serierecomendator.network.MovieDBApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitHelper {
    @Singleton
    @Provides
    fun makeRetrofitService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDBClient(retrofit: Retrofit): MovieDBApi {
        return retrofit.create(MovieDBApi::class.java)
    }
}