package com.dicoding.myapp.ui.common

sealed class UiState<out T: Any?> {
    object Loading: UiState<Nothing>()

    data class Success<out T: Any>(val data: Any?) : UiState<T>()
    data class Error(val errorMessage: String) : UiState<Nothing>()
}