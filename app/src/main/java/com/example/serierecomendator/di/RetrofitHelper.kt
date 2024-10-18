package com.example.serierecomendator.di

import com.example.serierecomendator.network.MangaDexApi
import com.example.serierecomendator.network.MovieDBApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    fun provideMovieDBClient(@Named("MovieDB") movieDBRetrofit: Retrofit): MovieDBApi {
        return movieDBRetrofit.create(MovieDBApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMangaApi(@Named("MangaDex") mangaDexRetrofit: Retrofit): MangaDexApi {
        return mangaDexRetrofit.create(MangaDexApi::class.java)
    }
}
