package com.example.serierecomendator.network

import com.example.serierecomendator.data.model.retrofit.SearchedNovel
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface WLNUpdatesApi {
    @POST("api")
    suspend fun searchAdvanced(
        @Body requestBody: RequestBody
    ): Response<SearchedNovel>
}
