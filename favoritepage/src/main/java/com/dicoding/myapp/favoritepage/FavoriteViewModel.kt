package com.dicoding.myapp.favoritepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.newsapp.core.data.Resource
import com.dicoding.newsapp.core.domain.model.News
import com.dicoding.newsapp.core.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.dicoding.myapp.ui.common.UiState

@HiltViewModel
class FavoriteViewModel @Inject constructor(val newsUseCase: NewsUseCase): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<News>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<News>> get() = _uiState

    init {
        showFavoriteNews()
    }

    fun showFavoriteNews() {
        viewModelScope.launch {
            newsUseCase.getFavoriteNews().collect{ resource ->
                _uiState.value = when (resource) {
                    is Resource.Success -> com.dicoding.myapp.ui.common.UiState.Success(resource.data)
                    is Resource.Error -> com.dicoding.myapp.ui.common.UiState.Error(resource.message.toString())
                    is Resource.Loading -> com.dicoding.myapp.ui.common.UiState.Loading
                }
            }
        }
    }
}