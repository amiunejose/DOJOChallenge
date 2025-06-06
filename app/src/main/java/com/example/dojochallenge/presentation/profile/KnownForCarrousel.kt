package com.example.dojochallenge.presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dojochallenge.data.model.KnownForImageModel
import com.example.dojochallenge.presentation.layouts.MovieCard
import com.example.dojochallenge.presentation.layouts.Text_h2

@Composable
fun KnownForCarrousel(images: List<KnownForImageModel>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text_h2("Known For", modifier = Modifier.padding(10.dp))
        LazyRow {
            items(images) { image ->
                MovieCard(image.title, image.fullPosterPathImageW300xH450)
            }
        }
    }
}


@Preview
@Composable
fun KnownForCarrouselPreview() {
    KnownForCarrousel(
        listOf(
            KnownForImageModel(),
            KnownForImageModel(),
            KnownForImageModel(),
            KnownForImageModel()
        )
    )
}
