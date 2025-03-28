package com.example.dojochallenge.data.database

import android.app.Application
import androidx.room.Room
import com.example.dojochallenge.data.database.converters.KnownForImageConverter
import com.example.dojochallenge.data.database.dao.TMDBMovieDao
import com.example.dojochallenge.data.database.dao.TMDBPopularPeopleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DATABASE_NAME = "TMDB_database"

    @Singleton
    @Provides
    @JvmStatic
    fun provideAppDatabase(
        application: Application,
        knownForImageConverter: KnownForImageConverter
    ) = Room
        .databaseBuilder(application, Database::class.java, DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .addTypeConverter(knownForImageConverter)
        .build()

    @Singleton
    @Provides
    @JvmStatic
    fun provideTMDBPopularPeopleDao(appDatabase: Database): TMDBPopularPeopleDao {
        return appDatabase.tmdbPopularPeopleDao()
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideKnownForImageConverter(json: Json): KnownForImageConverter {
        return KnownForImageConverter(json)
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideTMDBMovieDao(appDatabase: Database): TMDBMovieDao {
        return appDatabase.tmdbMovieDao()
    }
}
