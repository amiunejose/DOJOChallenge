package com.example.dojochallenge.data.network.movies

import com.example.dojochallenge.data.dto.TMDBMovieModelDTO
import com.example.dojochallenge.data.dto.TMDBMovieListDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TMDBMoviesService {

    @GET("movie/{id}")
    suspend fun getMovieById(@Path("id") id: Int): Response<TMDBMovieModelDTO>

    @GET("movie/popular")
    suspend fun getPopularMovieList(): Response<TMDBMovieListDTO>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovieList(): Response<TMDBMovieListDTO>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovieList(): Response<TMDBMovieListDTO>
}
