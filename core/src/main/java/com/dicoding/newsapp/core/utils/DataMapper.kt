package com.dicoding.newsapp.core.utils

import com.dicoding.newsapp.core.data.source.local.entity.NewsEntitiy
import com.dicoding.newsapp.core.data.source.remote.response.ArticlesItem
import com.dicoding.newsapp.core.domain.model.News

object DataMapper {
    fun mapResponseToDomain(input: List<ArticlesItem>): List<News> {
        val newsList = ArrayList<News>()
        input.map {
            val news = News(
                imageUrl = it.urlToImage,
                title = it.title,
                author = it.author,
                date = it.publishedAt,
                content = it.content,
                description = it.description,
                isFavorite = false
            )
            newsList.add(news)
        }
        return newsList
    }

    fun mapEntitiesToDomain(entity: List<NewsEntitiy>) =
        entity.map {input ->
            News(
                title = input.title,
                description = input.description,
                imageUrl = input.imageUrl,
                author = input.author,
                content = input.content,
                isFavorite = input.isFavorite,
                date = input.date
            )
        }

    fun mapDomainToEntity(input: News) = NewsEntitiy(
        title = input.title.toString(),
        description = input.description.toString(),
        imageUrl = input.imageUrl.toString(),
        author = input.author.toString(),
        content = input.content.toString(),
        isFavorite = input.isFavorite,
        date = input.date.toString()
    )
}