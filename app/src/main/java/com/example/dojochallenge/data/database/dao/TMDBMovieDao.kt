package com.example.dojochallenge.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dojochallenge.data.database.entities.TMDBMovieEntity

@Dao
interface TMDBMovieDao {

    @Query("SELECT * FROM TMDBMovieEntity WHERE category = :category")
    suspend fun getMovieListByCategory(category: String): List<TMDBMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(list: List<TMDBMovieEntity>)
}
