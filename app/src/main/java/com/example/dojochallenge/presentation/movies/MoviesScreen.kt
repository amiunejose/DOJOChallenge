package com.example.dojochallenge.presentation.movies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dojochallenge.presentation.layouts.TMDBHeader

@Composable
fun MoviesScreen(
    moviesScreenViewModel: MoviesScreenViewModel = hiltViewModel()
) {
    val popularMoviesUiState by moviesScreenViewModel.popularMoviesUiState.collectAsStateWithLifecycle()
    val topRatedMoviesUiState by moviesScreenViewModel.topRatedMoviesUiState.collectAsStateWithLifecycle()
    val nowPlayingMoviesUiState by moviesScreenViewModel.nowPlayingMoviesUiState.collectAsStateWithLifecycle()

    val popularMoviesList by moviesScreenViewModel.popularMoviesList.collectAsStateWithLifecycle()
    val topRatedMoviesList by moviesScreenViewModel.topRatedMoviesList.collectAsStateWithLifecycle()
    val nowPlayingMoviesList by moviesScreenViewModel.nowPlayingMoviesList.collectAsStateWithLifecycle()

    LazyColumn {
        item { TMDBHeader("Movies Leaderboard") }
        items(
            listOf(
                "Popular Movies" to popularMoviesList,
                "Top Rated Movies" to topRatedMoviesList,
                "Now Playing Movies" to nowPlayingMoviesList
            )
        ) { (title, moviesList) ->
            MoviesCarousel(title, moviesList)
            HorizontalDivider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(horizontal = 15.dp))
        }
    }
}
