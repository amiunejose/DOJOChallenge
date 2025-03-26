package com.example.dojochallenge.data.model

import com.google.gson.annotations.SerializedName

data class TMDBMovieModel(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("original_title") val originalTitle: String = "",
    @SerializedName("overview") val overview: String = ""
)
