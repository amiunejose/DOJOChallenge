package com.example.dojochallenge.presentation.movies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dojochallenge.data.model.TMDBMovieModel
import com.example.dojochallenge.presentation.layouts.MovieCard
import com.example.dojochallenge.presentation.layouts.Text_h2

@Composable
fun MoviesCarousel(title: String, moviesData: List<TMDBMovieModel>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(300.dp)
    ) {
        Text_h2(title, modifier = Modifier.padding(10.dp))
        LazyRow {
            items(moviesData) { movies ->
                MovieCard(movies.originalTitle, movies.fullPosterPathImageW300xH450)
            }
        }
    }
}


@Preview
@Composable
fun MoviesCarouselPreview() {
    MoviesCarousel(
        "Popular Movies", listOf(
            TMDBMovieModel(),
            TMDBMovieModel(),
            TMDBMovieModel(),
            TMDBMovieModel(),
            TMDBMovieModel(),
            TMDBMovieModel()
        )
    )
}
