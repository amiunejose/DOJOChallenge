package com.example.dojochallenge.data.dto

import com.example.dojochallenge.data.model.TMDBMovieModel
import com.google.gson.annotations.SerializedName

data class TMDBMovieModelListDTO(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<TMDBMovieModelDTO>
)

fun TMDBMovieModelListDTO.toDomainModel(): List<TMDBMovieModel> {
    return results.map { it.toDomainModel() }
}

data class TMDBMovieModelDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPathImage: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Float
)

fun TMDBMovieModelDTO.toDomainModel(): TMDBMovieModel {
    return try {
        TMDBMovieModel(
            id = id,
            originalTitle = originalTitle,
            overview = overview,
            posterPathImage = posterPathImage,
            releaseDate = releaseDate,
            voteAverage = voteAverage
        )
    } catch (e: Exception) {
        TMDBMovieModel()
    }
}
