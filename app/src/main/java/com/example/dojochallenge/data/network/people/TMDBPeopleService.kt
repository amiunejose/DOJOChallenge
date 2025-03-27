package com.example.dojochallenge.data.network.people

import com.example.dojochallenge.data.dto.TMBDPopularPeopleListDTO
import com.example.dojochallenge.data.dto.TMDBPeopleDetailsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TMDBPeopleService {

    @GET("person/popular")
    suspend fun getPopularPeopleList(): Response<TMBDPopularPeopleListDTO>

    @GET("person/{id}")
    suspend fun getPeopleDetailById(@Path("id") id: Int): Response<TMDBPeopleDetailsDTO>
}
