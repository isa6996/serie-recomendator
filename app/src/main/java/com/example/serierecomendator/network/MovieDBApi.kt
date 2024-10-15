package com.example.serierecomendator.network

import com.example.serierecomendator.data.model.retrofit.SearchedMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieDBApi {
    @GET("search/{type}?")
    suspend fun listSeries(
        @Path("type") type: String,
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("query") query: String,
        @Query("language") language: String ="es-ES"
    ): Response<SearchedMovie>

}