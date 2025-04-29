package com.example.dojochallenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dojochallenge.data.database.converters.KnownForImageConverter
import com.example.dojochallenge.data.database.dao.TMDBMovieDao
import com.example.dojochallenge.data.database.dao.TMDBPopularPeopleDao
import com.example.dojochallenge.data.database.entities.TMDBMovieEntity
import com.example.dojochallenge.data.database.entities.TMDBPopularPeopleEntity

@Database(
    entities = [TMDBPopularPeopleEntity::class, TMDBMovieEntity::class],
    version = 5
)
@TypeConverters(value = [KnownForImageConverter::class])
abstract class Database: RoomDatabase() {

    abstract fun tmdbPopularPeopleDao(): TMDBPopularPeopleDao

    abstract fun tmdbMovieDao(): TMDBMovieDao
}
