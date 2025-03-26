package com.example.dojochallenge.data.repository

import android.util.Log
import com.example.dojochallenge.data.model.TMDBPeopleModel
import com.example.dojochallenge.data.network.people.TMDBPeopleApiClient
import com.example.dojochallenge.domain.repository.TMDBPeopleRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Named

class TMDBPeopleRepositoryImpl @Inject constructor(
    private val apiClient: TMDBPeopleApiClient,
    @Named("io") private val ioDispatcher: CoroutineDispatcher
) : TMDBPeopleRepository {

    override fun fetchPopularPeopleList(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        var popularPeopleList = emptyList<TMDBPeopleModel>() // esta variable deberia inicializarse con una llamada a la DB local
        try {
            val response = apiClient.fetchPopularPeopleList()
            if (response.isSuccessful) {
                response.body()?.let {
                    popularPeopleList = it.results
                    emit(popularPeopleList)
                } ?: onError("Null body response")
            } else {
                val errorMessage = response.errorBody()?.string() ?: response.message()
                onError(errorMessage)
            }
        } catch (e: Exception) {
            onError(e.message)
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)


    override fun fetchMostPopularPeople(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = fetchPopularPeopleList(onStart, onComplete, onError)
        .map { popularPeopleList ->
            popularPeopleList.firstOrNull() ?: throw NoSuchElementException("empty list error")
        }
}
