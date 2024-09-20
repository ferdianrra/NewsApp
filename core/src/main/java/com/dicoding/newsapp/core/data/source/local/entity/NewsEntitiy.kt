package com.dicoding.newsapp.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "news")
data class NewsEntitiy(
    @PrimaryKey
    @Nonnull
    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "imageUrl")
    var imageUrl: String,

    @ColumnInfo(name = "author")
    var author: String,

    @ColumnInfo(name = "data")
    var date: String,

    @ColumnInfo(name = "content")
    var content: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)