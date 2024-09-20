package com.dicoding.myapp.ui.component

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dicoding.myapp.ui.R
import com.dicoding.newsapp.core.utils.FormatDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsItem(
    imageUrl: String,
    title: String,
    author: String,
    date: String,
    modifier: Modifier = Modifier
) {
    val formatDate = FormatDate.formatDate(date)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = stringResource(R.string.news_image_description),
            modifier = modifier
                .padding(10.dp)
                .size(130.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop,
            onError = { error ->
                Log.e("AsyncImage", "Error loading image: ${error.result.throwable.message}")
            }
        )
        Column(
            modifier = modifier
                .wrapContentHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = modifier.padding(top = 10.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                text = title
            )
            Text(
                modifier = modifier.padding(top = 10.dp),
                fontSize = 12.sp,

                text = "$author \t $formatDate"
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun NewsItemPreview() {
    NewsItem(
        imageUrl = "https://mtgrocks.com/wp-content/uploads/2024/09/kona-rescue-beastie-art.jpg",
        title = "MTG Best Duskmourn Commander Cards - MTG Rocks",
        author = "Zachary Fink",
        date = "14 Agustus 2024",
    )
}