package com.dicoding.newsapp.core.data.source.remote.network

import com.dicoding.newsapp.core.data.source.remote.response.ListNewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything")
    suspend fun getListGeneral(
        @Query("q") q:String
    ): ListNewsResponse

    suspend fun getListByTitle(
        @Query("q") q:String
    ): ListNewsResponse
}