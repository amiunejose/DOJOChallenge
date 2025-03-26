package com.example.dojochallenge.data.network.people

import com.example.dojochallenge.data.model.TMBDPopularPeopleListResponse
import retrofit2.Response
import retrofit2.http.GET

interface TMDBPeopleService {

    @GET("person/popular")
    suspend fun getPopularPeopleList(): Response<TMBDPopularPeopleListResponse>
}
