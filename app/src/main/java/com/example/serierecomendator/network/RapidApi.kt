package com.example.serierecomendator.network

import com.example.serierecomendator.data.model.retrofit.ResultWebtoon
import com.example.serierecomendator.data.model.retrofit.SearchedWebtoon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RapidApi {
    @GET("canvas/search")
    suspend fun searchCanvasWebtoon(
        @Query("startIndex") startIndex: Int = 0,
        @Query("pageSize") pageSize: Int = 20,
        @Query("query") query: String,
        @Query("language") language: String = "en"
    ): Response<ResultWebtoon>
}


