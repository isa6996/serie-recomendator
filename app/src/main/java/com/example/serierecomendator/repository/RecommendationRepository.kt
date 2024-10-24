package com.example.serierecomendator.repository

import android.util.Log
import com.example.serierecomendator.data.model.retrofit.SearchedMovie
import com.example.serierecomendator.data.model.retrofit.MOVIEDB_API_KEY
import com.example.serierecomendator.data.model.retrofit.SearchedTv
import com.example.serierecomendator.network.MovieDBApi
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *  Clase Repository para la API de películas.
 *  Pone a disposición de la clase MovieDBApi para llamar a la API de películas.
 */
@Module
@InstallIn(SingletonComponent::class)
class RecommendationRepository @Inject constructor(// Inyección de dependencias
    private val movieDBApi: MovieDBApi
) {
    suspend fun getTvSeries(title: String, type: String): SearchedTv{
        try {
            Log.d("MovieDBAPI", "sara : " + movieDBApi.listSeries(MOVIEDB_API_KEY, "EU", title, "es-ES"))
            return withContext(Dispatchers.IO) {
                val response = movieDBApi.listSeries( MOVIEDB_API_KEY, "EU", title, "es-ES")
                response.body()!!

            }

        }catch (e: Exception){
            Log.d("MovieDBAPI", "Error: " + e.message)
            Log.d("MovieDBAPI", "Error: " + e.stackTrace)
            Log.d("MovieDBAPI", "Error: " + e.cause)

            return null as SearchedTv
        }
    }
    suspend fun getMovies(title: String, type: String): SearchedMovie {
        try {
            Log.d("MovieDBAPI", "sara : " + movieDBApi.listSeries(MOVIEDB_API_KEY, "EU", title, "es-ES"))
            return withContext(Dispatchers.IO) {
                val response = movieDBApi.listMovies(MOVIEDB_API_KEY, "EU", title, "es-ES")
                response.body()!!

            }

        } catch (e: Exception) {
            Log.d("MovieDBAPI", "Error: " + e.message)
            Log.d("MovieDBAPI", "Error: " + e.stackTrace)
            Log.d("MovieDBAPI", "Error: " + e.cause)

            return null as SearchedMovie
        }
    }
}
