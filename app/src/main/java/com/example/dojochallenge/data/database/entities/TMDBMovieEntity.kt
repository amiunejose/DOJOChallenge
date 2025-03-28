package com.example.dojochallenge.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TMDBMovieEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo("original_title") val originalTitle: String,
    @ColumnInfo("overview") val overview: String,
    @ColumnInfo("poster_path") val posterPathImage: String,
    @ColumnInfo("release_date") val releaseDate: String,
    @ColumnInfo("vote_average") val voteAverage: Float,
    @ColumnInfo("category") val category: String
)
