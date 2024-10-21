package com.example.serierecomendator.di

import android.util.Log
import com.example.serierecomendator.data.model.retrofit.RAPIDAPI_API_KEY
import com.example.serierecomendator.data.model.retrofit.RAPIDAPI_HOST
import com.example.serierecomendator.network.MangaDexApi
import com.example.serierecomendator.network.MovieDBApi
import com.example.serierecomendator.network.RapidApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/*
 * Clase que proporciona una instancia de Retrofit para la API de películas.
 * @Singleton para que solo exista una instancia de Retrofit en toda la aplicación
 * @Provides para que Dagger hilt inyecte la dependencia
 * seleccioa la URL de la API de películas (https://api.themoviedb.org/3/) y el tipo de conversión
 * de datos (Gson) para la API de películas.
 */
@Module
@InstallIn(SingletonComponent::class)
object RetrofitHelper {

    @Singleton
    @Provides
    @Named("MovieDB")
    fun provideMovieDBRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Named("MangaDex")
    fun provideMangaDexRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.mangadex.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Named("Webtoon")
    fun provideRapidApiRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://webtoon.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().apply {
                    addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .addHeader("X-RapidAPI-Key", RAPIDAPI_API_KEY)
                            .addHeader("X-RapidAPI-Host", RAPIDAPI_HOST)
                            .addHeader("Content-Type", "application/json")
                            .build()
                        Log.d("webtoon", "Request URL: ${request.url}")
                        chain.proceed(request)
                    }
                }.build()
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDBClient(@Named("MovieDB") movieDBRetrofit: Retrofit): MovieDBApi {
        return movieDBRetrofit.create(MovieDBApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMangaApi(@Named("MangaDex") mangaDexRetrofit: Retrofit): MangaDexApi {
        return mangaDexRetrofit.create(MangaDexApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRapidApi(@Named("Webtoon") webtoonRetrofit: Retrofit): RapidApi {
        return webtoonRetrofit.create(RapidApi::class.java)
    }

}
