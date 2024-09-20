package com.dicoding.newsapp.core.domain.usecase

import com.dicoding.newsapp.core.data.Resource
import com.dicoding.newsapp.core.data.source.remote.network.ApiResponse
import com.dicoding.newsapp.core.domain.model.News
import com.dicoding.newsapp.core.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsInteractor @Inject constructor(private val newsRepository: INewsRepository): NewsUseCase {
    override  fun getNews(): Flow<Resource<List<News>>> {
        return newsRepository.getGeneralNews()
    }

    override fun getDetailNews(title: String): Resource<News> {
        return newsRepository.getDetailNews(title)
    }

    override suspend fun setFavoriteNews(news: News) {
        return newsRepository.setFavoriteNews(news)
    }

    override suspend fun deleteFavoriteNews(news: News) {
        return newsRepository.deleteFavoriteNews(news)
    }

    override fun getFavoriteNews(): Flow<Resource<List<News>>> {
        return newsRepository.getFavoriteNews()
    }

    override suspend fun isFavorite(title: String): Boolean {
        return newsRepository.isFavorite(title)
    }

    override fun getNewsByTitle(title: String): Flow<Resource<List<News>>> {
        return newsRepository.getNewsByTitle(title)
    }
}