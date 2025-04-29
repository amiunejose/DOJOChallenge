package com.example.dojochallenge.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dojochallenge.data.database.entities.TMDBPopularPeopleEntity

@Dao
interface TMDBPopularPeopleDao {

    @Query("SELECT * FROM TMDBPopularPeopleEntity ORDER BY orderId")
    suspend fun getPeopleList():List<TMDBPopularPeopleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeopleList(list: List<TMDBPopularPeopleEntity>)
}