package com.dicoding.newsapp.core.data.source.local

import com.dicoding.newsapp.core.data.source.local.entity.NewsEntitiy
import com.dicoding.newsapp.core.data.source.local.room.NewsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val newsDao: NewsDao){
    fun getFavoriteNews(): Flow<List<NewsEntitiy>> {
        return newsDao.getFavoriteNews()
    }

    suspend fun insertNews(news: NewsEntitiy) {
        return newsDao.insertNews(news)
    }

    suspend fun deleteNews(news: NewsEntitiy) {
        return newsDao.deleteFavoriteNews(news)
    }

    suspend fun isFavorite(title: String): Boolean {
        return newsDao.isNewsInFavorites(title)
    }



}