package com.dicoding.myapp.favoritepage

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.myapp.ui.common.UiState
import com.dicoding.myapp.ui.component.ShowError
import com.dicoding.myapp.ui.component.ShowLoading
import com.dicoding.newsapp.core.domain.model.News
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    navigateBack:() -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxWidth(),
        topBar = {
            AppBar(onBackClick = navigateBack, modifier)
        }
    ) {innerPadding ->
        val viewModel: FavoriteViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsState()
        when(uiState) {
            is UiState.Error -> {
                ShowError {
                    viewModel.viewModelScope.launch {
                        viewModel.showFavoriteNews()
                    }
                }
            }
            UiState.Loading ->  {
                ShowLoading()
            }
            is UiState.Success -> {
                FavoriteContent(
                    modifier=modifier.padding(innerPadding),
                    listNews = (uiState as com.dicoding.myapp.ui.common.UiState.Success<List<News>>).data as List<News>
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FavoriteContent(
    modifier: Modifier = Modifier,
    listNews: List<News>
) {
    Log.e("FavoriteScreen", listNews.toString())
    LazyColumn(
        modifier = modifier
    ) {
        items(listNews) { data ->
            com.dicoding.myapp.ui.component.NewsItem(
                imageUrl = data.imageUrl!!,
                title = data.title!!,
                author = data.author!!,
                date = data.date!!,
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.systemBars),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        },
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Favorite",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        },
    )
}