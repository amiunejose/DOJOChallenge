package com.example.dojochallenge.data.repository

import androidx.annotation.WorkerThread
import com.example.dojochallenge.data.dto.toDomainModelList
import com.example.dojochallenge.data.model.TMDBPopularPeopleModel
import com.example.dojochallenge.data.model.updateWithDetail
import com.example.dojochallenge.data.network.people.TMDBPeopleApiClient
import com.example.dojochallenge.domain.repository.TMDBPeopleRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Named

class TMDBPeopleRepositoryImpl @Inject constructor(
    private val apiClient: TMDBPeopleApiClient,
    @Named("io") private val ioDispatcher: CoroutineDispatcher
) : TMDBPeopleRepository {

    @WorkerThread
    override fun fetchPopularPeopleList(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        var popularPeopleList = emptyList<TMDBPopularPeopleModel>() // esta variable deberia inicializarse con una llamada a la DB local
        try {
            val response = apiClient.fetchPopularPeopleList()
            if (response.isSuccessful) {
                response.body()?.let {
                    popularPeopleList = it.toDomainModelList()
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


    @WorkerThread
    override fun fetchMostPopularPeople(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = fetchPopularPeopleList(onStart, onComplete, onError)
        .transform { popularPeopleList ->
            val mostPopular = popularPeopleList.firstOrNull()
                ?: throw NoSuchElementException("Empty list error")
            val detailResponse = apiClient.fetchPeopleDetailById(mostPopular.id)
            if (detailResponse.isSuccessful) {
                detailResponse.body()?.let { detailData ->
                    emit(mostPopular.updateWithDetail(detailData))
                } ?: onError("Detail body is null")
            } else {
                onError(detailResponse.errorBody()?.string() ?: detailResponse.message())
            }
        }
}
