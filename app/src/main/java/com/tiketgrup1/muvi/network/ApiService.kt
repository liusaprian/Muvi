package com.tiketgrup1.muvi.network

import com.tiketgrup1.muvi.model.MovieResponse
import com.tiketgrup1.muvi.utils.ResponseConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/{category}")
    suspend fun getMovies(
        @Path("category") category: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ) : MovieResponse
}

val api: ApiService by lazy {
    Retrofit.Builder()
        .baseUrl(ResponseConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}