package com.example.dojochallenge.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.dojochallenge.R
import com.example.dojochallenge.data.model.KnownForImage
import com.example.dojochallenge.presentation.layouts.Text_h2
import com.example.dojochallenge.presentation.layouts.Text_h4
import com.example.dojochallenge.presentation.layouts.Text_h5
import com.example.dojochallenge.ui.theme.Red

@Composable
fun KnownForCarrousel(images: List<KnownForImage>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        item { Text_h2("Known For", modifier = Modifier.padding(10.dp)) }
        item {
            LazyRow {
                items(images) { image ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .padding(5.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            model = image.fullPosterPathImageW300xH450,
                            contentDescription = image.title,
                            placeholder = painterResource(R.drawable.default_profile_image_300_400)
                        )
                        Box(contentAlignment = Alignment.Center) {
                            Text_h5(image.title, modifier = Modifier.width(80.dp), textAlign = TextAlign.Center)
                        }

                    }
                }
            }
        }
    }
}
