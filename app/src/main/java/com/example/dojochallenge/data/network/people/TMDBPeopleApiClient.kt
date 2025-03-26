package com.example.dojochallenge.data.network.people

import android.util.Log
import com.example.dojochallenge.data.model.TMBDPopularPeopleListResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import javax.inject.Inject

class TMDBPeopleApiClient @Inject constructor(private val service: TMDBPeopleService) {

    suspend fun fetchPopularPeopleList(): Response<TMBDPopularPeopleListResponse> {
        return try {
            service.getPopularPeopleList()
        } catch (e: Exception) {
            Log.e("fetchPopularPeopleList", "Fetching Popular Person ERROR", e)

            val errorJson = "{\"error\": \"${e.message ?: "StackTrace: ${e.stackTraceToString()}"}\"}"
            val errorResponseBody = errorJson.toResponseBody("application/json".toMediaTypeOrNull())
            Response.error(500, errorResponseBody)
        }
    }
}