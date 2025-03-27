package com.example.dojochallenge.data.network.people

import android.util.Log
import com.example.dojochallenge.data.dto.TMBDPopularPeopleListDTO
import com.example.dojochallenge.data.dto.TMDBPeopleDetailsDTO
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import javax.inject.Inject

class TMDBPeopleApiClient @Inject constructor(private val service: TMDBPeopleService) {

    suspend fun fetchPopularPeopleList(): Response<TMBDPopularPeopleListDTO> {
        return try {
            service.getPopularPeopleList()
        } catch (e: Exception) {
            Log.e("fetchPopularPeopleList", "Fetching Popular Person ERROR", e)

            val errorJson = "{\"error\": \"${e.message ?: "StackTrace: ${e.stackTraceToString()}"}\"}"
            val errorResponseBody = errorJson.toResponseBody("application/json".toMediaTypeOrNull())
            Response.error(500, errorResponseBody)
        }
    }

    suspend fun fetchPeopleDetailById(id: Int): Response<TMDBPeopleDetailsDTO> {
        return try {
            service.getPeopleDetailById(id)
        } catch (e: Exception) {
            Log.e("getPeopleById", "Fetching Person by Id ERROR", e)

            val errorJson = "{\"error\": \"${e.message ?: "StackTrace: ${e.stackTraceToString()}"}\"}"
            val errorResponseBody = errorJson.toResponseBody("application/json".toMediaTypeOrNull())
            Response.error(500, errorResponseBody)
        }
    }
}