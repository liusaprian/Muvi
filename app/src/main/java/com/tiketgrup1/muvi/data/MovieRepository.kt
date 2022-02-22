package com.tiketgrup1.muvi.data

import com.tiketgrup1.muvi.BuildConfig
import com.tiketgrup1.muvi.network.ApiService
import com.tiketgrup1.muvi.network.api
import com.tiketgrup1.muvi.utils.ResponseConfig

class MovieRepository {

    private val service: ApiService = api

    suspend fun getMovies(category: String) = service.getMovies(
        category,
        BuildConfig.API_KEY,
        ResponseConfig.LANGUAGE,
        ResponseConfig.PAGE
    )

    suspend fun getCasts(id: Int) = service.getCasts(
        id,
        BuildConfig.API_KEY,
        ResponseConfig.LANGUAGE,
    )
}