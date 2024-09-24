package com.example.serierecomendator.network

import com.example.serierecomendator.data.model.retrofit.SearchedMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieDBApi {
    @GET("search/tv?")
    suspend fun listSeries(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("query") query: String,
        @Query("language") language: String ="es-ES"
    ): Response<SearchedMovie>

}