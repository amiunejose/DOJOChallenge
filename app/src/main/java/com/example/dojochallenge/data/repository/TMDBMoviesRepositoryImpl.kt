package com.example.dojochallenge.data.repository

import androidx.annotation.WorkerThread
import com.example.dojochallenge.data.database.dao.TMDBMovieDao
import com.example.dojochallenge.data.database.mapper.asDomain
import com.example.dojochallenge.data.database.mapper.asEntity
import com.example.dojochallenge.data.dto.toDomainModel
import com.example.dojochallenge.data.model.TMDBMovieModel
import com.example.dojochallenge.data.network.movies.TMDBMoviesAPIClient
import com.example.dojochallenge.domain.repository.TMDBMoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Named

class TMDBMoviesRepositoryImpl @Inject constructor(
    private val apiClient: TMDBMoviesAPIClient,
    @Named("io") private val ioDispatcher: CoroutineDispatcher,
    private val movieDao: TMDBMovieDao
) : TMDBMoviesRepository {

    enum class MovieCategory(val category: String) {
        POPULAR("popular"),
        TOP_RATED("top_rated"),
        NOW_PLAYING("now_playing")
    }

    @WorkerThread
    override fun fetchPopularMovieList(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        var movieList = movieDao.getMovieListByCategory(MovieCategory.POPULAR.category).asDomain() // Get from DB first
        if (movieList.isEmpty()) {
            try {
                val response = apiClient.fetchPopularMovieList()
                if (response.isSuccessful) {
                    response.body()?.let {
                        movieList = it.toDomainModel(MovieCategory.POPULAR) // Get from API
                        movieDao.insertMovieList(movieList.asEntity()) // Updated DB
                        emit(movieDao.getMovieListByCategory(MovieCategory.POPULAR.category).asDomain()) // Get from updated DB
                    } ?: onError("Null body response")
                } else {
                    val errorMessage = response.errorBody()?.string() ?: response.message()
                    onError(errorMessage)
                }
            } catch (e: Exception) {
                onError(e.message)
            }
        } else {
            emit(movieList)
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)

    @WorkerThread
    override fun fetchTopRatedMovieList(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        var movieList = movieDao.getMovieListByCategory(MovieCategory.TOP_RATED.category).asDomain() // Get from DB first
        if (movieList.isEmpty()) {
            try {
                val response = apiClient.fetchTopRatedMovieList()
                if (response.isSuccessful) {
                    response.body()?.let {
                        movieList = it.toDomainModel(MovieCategory.TOP_RATED) // Get from API
                        movieDao.insertMovieList(movieList.asEntity()) // Updated DB
                        emit(movieDao.getMovieListByCategory(MovieCategory.TOP_RATED.category).asDomain()) // Get from updated DB
                    } ?: onError("Null body response")
                } else {
                    val errorMessage = response.errorBody()?.string() ?: response.message()
                    onError(errorMessage)
                }
            } catch (e: Exception) {
                onError(e.message)
            }
        } else {
            emit(movieList)
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)

    @WorkerThread
    override fun fetchNowPlayingMovieList(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        var movieList = movieDao.getMovieListByCategory(MovieCategory.NOW_PLAYING.category).asDomain() // Get from DB first
        if (movieList.isEmpty()) {
            try {
                val response = apiClient.fetchNowPlayingMovieList()
                if (response.isSuccessful) {
                    response.body()?.let {
                        movieList = it.toDomainModel(MovieCategory.NOW_PLAYING) // Get from API
                        movieDao.insertMovieList(movieList.asEntity()) // Updated DB
                        emit(movieDao.getMovieListByCategory(MovieCategory.NOW_PLAYING.category).asDomain()) // Get from updated DB
                    } ?: onError("Null body response")
                } else {
                    val errorMessage = response.errorBody()?.string() ?: response.message()
                    onError(errorMessage)
                }
            } catch (e: Exception) {
                onError(e.message)
            }
        } else {
            emit(movieList)
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)

    @WorkerThread
    override fun fetchMovieById(
        id: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        var movie = TMDBMovieModel()
        try {
            val response = apiClient.fetchMovieById(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    movie = it.toDomainModel()
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
