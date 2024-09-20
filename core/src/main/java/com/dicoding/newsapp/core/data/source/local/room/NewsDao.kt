package com.dicoding.newsapp.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dicoding.newsapp.core.data.source.local.entity.NewsEntitiy
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getFavoriteNews(): Flow<List<NewsEntitiy>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: NewsEntitiy)

    @Delete
    suspend fun deleteFavoriteNews(news: NewsEntitiy)

    @Query("SELECT EXISTS(SELECT 1 FROM news WHERE title = :title)")
    suspend fun isNewsInFavorites(title: String): Boolean
}