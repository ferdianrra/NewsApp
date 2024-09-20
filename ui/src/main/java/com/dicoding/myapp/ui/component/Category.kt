package com.dicoding.myapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.myapp.ui.R

@Composable
fun Category(
    modifier: Modifier = Modifier,
    categories: List<String>
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(categories) { category ->
            Text(
                text = category,
                modifier = modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = colorResource(id =  R.color.light_blue))
                    .padding(12.dp)
            )
        }
    }
}

@Preview
@Composable
private fun CategoryPreview() {
    Category(categories = stringArrayResource(id = R.array.category).toList())
}