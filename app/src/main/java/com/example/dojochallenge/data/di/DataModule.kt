package com.example.dojochallenge.data.di

import com.example.dojochallenge.data.repository.TMDBMoviesRepositoryImpl
import com.example.dojochallenge.data.repository.TMDBPeopleRepositoryImpl
import com.example.dojochallenge.domain.repository.TMDBMoviesRepository
import com.example.dojochallenge.domain.repository.TMDBPeopleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun providePeopleRepository(tmdbPeopleRepositoryImpl: TMDBPeopleRepositoryImpl): TMDBPeopleRepository

    @Binds
    fun provideMoviesRepository(tmdbMoviesRepositoryImpl: TMDBMoviesRepositoryImpl): TMDBMoviesRepository
}
