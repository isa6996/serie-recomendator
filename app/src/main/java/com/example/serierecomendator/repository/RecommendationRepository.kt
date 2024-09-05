package com.example.serierecomendator.repository

import com.example.serierecomendator.data.model.retrofit.SearchedMovie
import com.example.serierecomendator.network.MovieDBApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RecommendationRepository @Inject constructor(
    private val movieDBApi: MovieDBApi
) {
    suspend fun getMovies(): SearchedMovie{
        return withContext(Dispatchers.IO){
            val response = movieDBApi.listSeries("e32dd4598e7cb2f7dc42b4bf44d971c3", "US")
            response.body()!!
        }
    }
}