package com.example.dojochallenge.domain.repository

import com.example.dojochallenge.data.model.TMDBMovieModel
import kotlinx.coroutines.flow.Flow

interface TMDBMoviesRepository {

    fun fetchMovieById(
        id: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<TMDBMovieModel>

    fun fetchPopularMovieList(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<TMDBMovieModel>>

    fun fetchTopRatedMovieList(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<TMDBMovieModel>>

    fun fetchNowPlayingMovieList(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<TMDBMovieModel>>
}
