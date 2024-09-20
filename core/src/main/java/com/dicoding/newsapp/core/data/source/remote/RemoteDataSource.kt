package com.dicoding.newsapp.core.data.source.remote

import android.util.Log
import com.dicoding.newsapp.core.data.source.remote.network.ApiResponse
import com.dicoding.newsapp.core.data.source.remote.network.ApiService
import com.dicoding.newsapp.core.data.source.remote.response.ArticlesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

     fun getListGeneral(): Flow<ApiResponse<List<ArticlesItem>>> {
        return flow {
            try {
                val response =apiService.getListGeneral("college")
                val dataArray = response.articles
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.articles))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getListByTitle(title: String): Flow<ApiResponse<List<ArticlesItem>>> {
        return flow {
            try {
                val response =apiService.getListGeneral(title)
                val dataArray = response.articles
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.articles))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}