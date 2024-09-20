package com.dicoding.newsapp.detailpage

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.dicoding.myapp.ui.component.ShowError
import com.dicoding.myapp.ui.component.ShowLoading
import com.dicoding.newsapp.core.domain.model.News
import com.dicoding.newsapp.core.utils.FormatDate
import kotlinx.coroutines.launch
import com.dicoding.myapp.ui.common.UiState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    title: String,
    navigateBack:() -> Unit
) {
    val viewModel: DetailViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState(initial = UiState.Loading)
    val isFavorite by viewModel.isFavorite.collectAsState()

    LaunchedEffect(uiState) {
        if (uiState is UiState.Success) {
            viewModel.checkFavorite(title)
        }
    }
    val showFab = uiState is UiState.Success
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            if (showFab) {
                FavoriteFloatingActionButton(
                    modifier = modifier,
                    viewModel = viewModel,
                    news = (uiState as UiState.Success).data as News,
                    isFavorite = isFavorite
                )
            }
        }
    ) {

        when(uiState) {
            is UiState.Error -> {
                ShowError {
                    viewModel.getDetailNews(title)
                }
            }
            is UiState.Loading -> {
                ShowLoading()
                viewModel.getDetailNews(title)
            }
            is UiState.Success -> {
                val data = (uiState as UiState.Success).data as News
                DetailContent(
                    author = data.author.toString(),
                    content = data.content.toString(),
                    date = data.date.toString(),
                    imageUri = data.imageUrl.toString(),
                    title = data.title.toString(),
                    description = data.description.toString()
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    author: String, 
    content: String,
    date: String,
    imageUri: String,
    title: String,
    description: String
) {
    val formatDate = FormatDate.formatDate(date)
    Column(
    ) {
        val colorStops = arrayOf(
            0.005f to Color.Black,
            0.4f to Color.Transparent,
            1f to Color.Black
        )
        val brush = Brush.verticalGradient(colorStops = colorStops)
        Box(
            contentAlignment = Alignment.BottomStart
        ){
            AsyncImage(
                model = imageUri,
                contentDescription = stringResource(id = R.string.news_image_description),
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .height(350.dp)
                    .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
                    .drawWithContent {
                        drawContent()
                        drawRect(brush = brush)
                    }
                    .fillMaxWidth()

                ,
                )
            Column(
                modifier = modifier.padding(20.dp)
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                    ,
                    horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                    Text(
                        text = if (author.length > 20) author.take(20) + "..." else author,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = formatDate,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Column(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 20.dp)

        ) {
            Text(
                text = description,
                fontSize = 14.sp,
                textAlign = TextAlign.Start,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = content,
                fontSize = 14.sp,
                textAlign = TextAlign.Start,
            )
        }
    }
}

@Composable
fun FavoriteFloatingActionButton(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel,
    news: News,
    isFavorite: Boolean
) {
    val coroutineScope = rememberCoroutineScope()
    FloatingActionButton(onClick = {
        if (isFavorite) {
            coroutineScope.launch {
                viewModel.deleteFavorite(news)
            }

        } else {
            coroutineScope.launch {
                viewModel.setFavorite(news)
            }
        }
    }) {
        if (isFavorite) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite",
                tint = Color.Red
            )
        } else {
            Icon(imageVector = Icons.Default.Favorite, contentDescription = "Delete Favorite")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun PreviewDetailContent() {
    DetailContent(author = "Ferdi", content = "Lorem Ipsum", date = "17 Agustus 1945", imageUri = " ", title = "Content App", description = "Description Content")
}