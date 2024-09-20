package com.dicoding.newsapp.detailpage

import androidx.lifecycle.ViewModel
import com.dicoding.myapp.ui.common.UiState
import com.dicoding.newsapp.core.data.Resource
import com.dicoding.newsapp.core.domain.model.News
import com.dicoding.newsapp.core.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val newsUseCase: NewsUseCase): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<News>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<News>> get() = _uiState

    private val _isFavorite: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> get() = _isFavorite

    fun getDetailNews(title: String) {
        _uiState.value = UiState.Loading
        when(val result = newsUseCase.getDetailNews(title)){
            is Resource.Error -> _uiState.value = UiState.Error(result.message.toString())
            is Resource.Loading -> _uiState.value = UiState.Loading
            is Resource.Success -> _uiState.value = UiState.Success(result.data)
        }
    }

    suspend fun setFavorite(news: News) {
        _isFavorite.value = true
        newsUseCase.setFavoriteNews(news)
    }

    suspend fun checkFavorite(title: String) {
       _isFavorite.value = newsUseCase.isFavorite(title)
    }

    suspend fun deleteFavorite(news: News) {
        _isFavorite.value = false
        newsUseCase.deleteFavoriteNews(news)
    }
}