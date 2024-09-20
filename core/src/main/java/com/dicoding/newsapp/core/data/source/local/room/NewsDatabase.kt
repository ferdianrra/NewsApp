package com.dicoding.newsapp.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.newsapp.core.data.source.local.entity.NewsEntitiy

@Database(entities = [NewsEntitiy::class], version = 1, exportSchema = false)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}