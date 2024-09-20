@file:Suppress("UNCHECKED_CAST")

package com.dicoding.newsapp.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.newsapp.core.domain.model.News
import com.dicoding.myapp.ui.common.UiState
import com.dicoding.myapp.ui.component.NewsItem
import com.dicoding.myapp.ui.component.SearchBar
import com.dicoding.myapp.ui.component.ShowError
import com.dicoding.myapp.ui.component.ShowLoading
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    when(uiState) {
        is UiState.Error -> {
            ShowError(onClick = {
                viewModel.viewModelScope.launch {
                    viewModel.getNews()
                }
            })
        }
        UiState.Loading -> {
            ShowLoading()
        }
        is UiState.Success -> {
            HomeContent(
                listNews = (uiState as UiState.Success<List<News>>).data as List<News>,
                navigateToDetail = navigateToDetail, viewModel = viewModel,
                modifier = modifier
            )
        }
    }
    viewModel.uiState.collectAsState().value.let {

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    listNews: List<News>,
    navigateToDetail:(String) -> Unit,
    viewModel: HomeViewModel
) {
    val filteredList = listNews.filter { item ->
        item.imageUrl != null && item.title != null && item.author != null && item.date != null && item.content != null
    }
    LazyColumn {
        item {
            SearchBar(
                onSearch = { title ->
                    viewModel.viewModelScope.launch {
                        viewModel.getNewsByTitle(title)
                    }
                }
            )
        }
        items(filteredList) { data ->
            NewsItem(
                imageUrl = data.imageUrl!!,
                title = data.title!!,
                author = data.author!!,
                date = data.date!!,
                modifier = modifier.clickable {
                    navigateToDetail(data.title!!)
                }
            )
        }
    }
}
