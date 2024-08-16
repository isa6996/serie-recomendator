package com.example.serierecomendator.data

import com.example.serierecomendator.data.model.RemoteResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {/*
    @GET("search/movie?query=el")
    suspend fun listSeries(
      //  @Path("type") type: String,
        @Query("api_key") apiKey: String,
        @Query("region") region: String

        ): RemoteResult

}

object RetrofitServiceFactory{
    fun  makeRetrofitService():RetrofitService{
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }*/
}