package com.dicoding.newsapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val imageUrl: String?,
    val title: String?,
    val author: String?,
    val date: String?,
    val content: String?,
    val description: String?,
    val isFavorite: Boolean
) : Parcelable
