package com.example.dojochallenge.data.dto

import com.example.dojochallenge.data.model.TMDBMovieModel
import com.example.dojochallenge.data.repository.TMDBMoviesRepositoryImpl
import com.google.gson.annotations.SerializedName

data class TMDBMovieListDTO(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<TMDBMovieModelDTO>
)

fun TMDBMovieListDTO.toDomainModel(category: TMDBMoviesRepositoryImpl.MovieCategory): List<TMDBMovieModel> {
    return results.map { it.toDomainModel(category = category.category) }
}

data class TMDBMovieModelDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPathImage: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Float
)

fun TMDBMovieModelDTO.toDomainModel(category: String = ""): TMDBMovieModel {
    return try {
        TMDBMovieModel(
            id = id,
            originalTitle = originalTitle,
            overview = overview,
            posterPathImage = posterPathImage,
            releaseDate = releaseDate,
            voteAverage = voteAverage,
            category = category
        )
    } catch (e: Exception) {
        TMDBMovieModel()
    }
}
