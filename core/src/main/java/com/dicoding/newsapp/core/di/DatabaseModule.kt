package com.dicoding.newsapp.core.di

import android.content.Context
import androidx.room.Room
import com.dicoding.newsapp.core.data.source.local.room.NewsDao
import com.dicoding.newsapp.core.data.source.local.room.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase = Room.databaseBuilder(
        context,
        NewsDatabase::class.java, "News.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun proviedNewsDao(database: NewsDatabase): NewsDao = database.newsDao()
}