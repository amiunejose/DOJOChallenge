package com.example.dojochallenge.data.network.movies

import android.util.Log
import com.example.dojochallenge.data.dto.TMDBMovieModelDTO
import com.example.dojochallenge.data.dto.TMDBMovieListDTO
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import javax.inject.Inject

class TMDBMoviesAPIClient @Inject constructor(private val service: TMDBMoviesService) {

    suspend fun fetchMovieById(id: Int): Response<TMDBMovieModelDTO> {
        return try {
            service.getMovieById(id)
        } catch (e: Exception) {
            Log.e("fetchMovieById", "Fetching movie ERROR", e)

            val errorJson = "{\"error\": \"${e.message ?: "StackTrace: ${e.stackTraceToString()}"}\"}"
            val errorResponseBody = errorJson.toResponseBody("application/json".toMediaTypeOrNull())
            Response.error(500, errorResponseBody)
        }
    }

    suspend fun fetchPopularMovieList(): Response<TMDBMovieListDTO> {
        return try {
            service.getPopularMovieList()
        } catch (e: Exception) {
            Log.e("fetchPopularMovieList", "Fetching popular movie list ERROR", e)

            val errorJson = "{\"error\": \"${e.message ?: "StackTrace: ${e.stackTraceToString()}"}\"}"
            val errorResponseBody = errorJson.toResponseBody("application/json".toMediaTypeOrNull())
            Response.error(500, errorResponseBody)
        }
    }

    suspend fun fetchTopRatedMovieList(): Response<TMDBMovieListDTO> {
        return try {
            service.getTopRatedMovieList()
        } catch (e: Exception) {
            Log.e("fetchTopRatedMovieList", "Fetching top rated movie list ERROR", e)

            val errorJson = "{\"error\": \"${e.message ?: "StackTrace: ${e.stackTraceToString()}"}\"}"
            val errorResponseBody = errorJson.toResponseBody("application/json".toMediaTypeOrNull())
            Response.error(500, errorResponseBody)
        }
    }

    suspend fun fetchNowPlayingMovieList(): Response<TMDBMovieListDTO> {
        return try {
            service.getNowPlayingMovieList()
        } catch (e: Exception) {
            Log.e("fetchNowPlayingMovieList", "Fetching now playing movie list ERROR", e)

            val errorJson = "{\"error\": \"${e.message ?: "StackTrace: ${e.stackTraceToString()}"}\"}"
            val errorResponseBody = errorJson.toResponseBody("application/json".toMediaTypeOrNull())
            Response.error(500, errorResponseBody)
        }
    }
}
