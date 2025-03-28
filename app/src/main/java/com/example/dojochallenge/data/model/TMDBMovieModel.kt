package com.example.dojochallenge.data.model

import com.example.dojochallenge.data.utils.ImageMapper
import kotlinx.serialization.Serializable

@Serializable
data class TMDBMovieModel(
    val id: Int = 0,
    val originalTitle: String = "",
    val overview: String = "",
    val posterPathImage: String = "",
    val releaseDate: String = "",
    val voteAverage: Float = 0f,
    val category: String = ""
){
    val fullPosterPathImageW300xH450: String
        get() = ImageMapper.getFullImagePathW300xH450(posterPathImage)

    val fullPosterPathImageW600xH900: String
        get() = ImageMapper.getFullImagePathW600xH900(posterPathImage)
}
