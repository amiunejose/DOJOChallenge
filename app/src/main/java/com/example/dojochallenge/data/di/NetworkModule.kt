package com.example.dojochallenge.data.di

import android.content.Context
import android.util.Log
import com.example.dojochallenge.R
import com.example.dojochallenge.data.network.movies.TMDBMoviesService
import com.example.dojochallenge.data.network.people.TMDBPeopleService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    @JvmStatic
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", context.getString(R.string.authorization_token))
                    .build()
                chain.proceed(request)
            }
            .build()


    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    @JvmStatic
    fun provideMoviesService(retrofit: Retrofit): TMDBMoviesService {
        return retrofit.create(TMDBMoviesService::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun providePeopleService(retrofit: Retrofit): TMDBPeopleService {
        return retrofit.create(TMDBPeopleService::class.java)
    }
}
