package com.example.dojochallenge.data.network.movies

import android.util.Log
import com.example.dojochallenge.data.model.TMDBMovieModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import javax.inject.Inject

class TMDBMoviesAPIClient @Inject constructor(private val service: TMDBMoviesService) {

    suspend fun fetchMovieById(id: Int): Response<TMDBMovieModel> {
        return try {
            service.getMovieById(id)
        } catch (e: Exception) {
            Log.e("fetchMovieById", "Fetching movie ERROR", e)

            val errorJson = "{\"error\": \"${e.message ?: "StackTrace: ${e.stackTraceToString()}"}\"}"
            val errorResponseBody = errorJson.toResponseBody("application/json".toMediaTypeOrNull())
            Response.error(500, errorResponseBody)
        }
    }
}
