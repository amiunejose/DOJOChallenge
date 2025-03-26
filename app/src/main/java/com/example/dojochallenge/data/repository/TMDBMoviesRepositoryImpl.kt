package com.example.dojochallenge.data.repository

import androidx.annotation.WorkerThread
import com.example.dojochallenge.data.model.TMDBMovieModel
import com.example.dojochallenge.data.network.movies.TMDBMoviesAPIClient
import com.example.dojochallenge.domain.repository.TMDBMoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Named

class TMDBMoviesRepositoryImpl @Inject constructor(
    private val apiClient: TMDBMoviesAPIClient,
    @Named("io") private val ioDispatcher: CoroutineDispatcher
) : TMDBMoviesRepository {

    @WorkerThread
    override fun fetchMovieById(
        id: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        var movie = TMDBMovieModel() // esta variable deberia inicializarse con una llamada a la DB local
        try {
            val response = apiClient.fetchMovieById(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    movie = it
                    emit(movie)
                } ?: onError("Null body response")
            } else {
                val errorMessage = response.errorBody()?.string() ?: response.message()
                onError(errorMessage)
            }
        } catch (e: Exception) {
            onError(e.message)
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
