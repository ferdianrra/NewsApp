package com.dicoding.newsapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.newsapp.core.data.Resource
import com.dicoding.newsapp.core.data.source.remote.network.ApiResponse
import com.dicoding.newsapp.core.domain.model.News
import com.dicoding.newsapp.core.domain.usecase.NewsUseCase
import com.dicoding.myapp.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val newsUseCase: NewsUseCase): ViewModel() {
    private val _uiState: MutableStateFlow<com.dicoding.myapp.ui.common.UiState<List<News>>> = MutableStateFlow(
        com.dicoding.myapp.ui.common.UiState.Loading)
    val uiState: StateFlow<com.dicoding.myapp.ui.common.UiState<List<News>>> get() = _uiState

    init {
        viewModelScope.launch {
            getNews()
        }
    }

    suspend fun getNews() {
        _uiState.value = com.dicoding.myapp.ui.common.UiState.Loading
        newsUseCase.getNews().collect { resource ->
            _uiState.value = when (resource) {
                is Resource.Success -> com.dicoding.myapp.ui.common.UiState.Success(resource.data)
                is Resource.Error -> com.dicoding.myapp.ui.common.UiState.Error(resource.message.toString())
                is Resource.Loading -> com.dicoding.myapp.ui.common.UiState.Loading
            }
        }
    }

    suspend fun getNewsByTitle(title: String) {
        _uiState.value = com.dicoding.myapp.ui.common.UiState.Loading
        newsUseCase.getNewsByTitle(title).collect { resource ->
            _uiState.value = when (resource) {
                is Resource.Success -> com.dicoding.myapp.ui.common.UiState.Success(resource.data)
                is Resource.Error -> com.dicoding.myapp.ui.common.UiState.Error(resource.message.toString())
                is Resource.Loading -> com.dicoding.myapp.ui.common.UiState.Loading
            }
        }
    }
}