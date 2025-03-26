package com.example.dojochallenge.data.network.movies

import com.example.dojochallenge.data.model.TMDBMovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TMDBMoviesService {

    @GET("movie/{id}")
    suspend fun getMovieById(@Path("id") id: Int): Response<TMDBMovieModel>
}
