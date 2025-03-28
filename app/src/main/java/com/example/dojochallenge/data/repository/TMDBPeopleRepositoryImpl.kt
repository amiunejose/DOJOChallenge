package com.example.dojochallenge.data.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.dojochallenge.data.database.dao.TMDBPopularPeopleDao
import com.example.dojochallenge.data.database.mapper.asDomain
import com.example.dojochallenge.data.database.mapper.asEntity
import com.example.dojochallenge.data.dto.toDomainModel
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
    @Named("io") private val ioDispatcher: CoroutineDispatcher,
    private val popularPeopleDao: TMDBPopularPeopleDao
) : TMDBPeopleRepository {

    @WorkerThread
    override fun fetchPopularPeopleList(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        var popularPeopleList = popularPeopleDao.getPeopleList().asDomain() // Get from DB first
        if (popularPeopleList.isEmpty()) {
            try {
                val response = apiClient.fetchPopularPeopleList()
                if (response.isSuccessful) {
                    response.body()?.let {
                        popularPeopleList = it.toDomainModel() // Get from API
                        popularPeopleDao.insertPeopleList(popularPeopleList.asEntity()) // Updated DB
                        emit(popularPeopleDao.getPeopleList().asDomain()) // Get from DB updated
                    } ?: onError("Null body response")
                } else {
                    val errorMessage = response.errorBody()?.string() ?: response.message()
                    onError(errorMessage)
                }
            } catch (e: Exception) {
                throw e
            }
        } else {
            emit(popularPeopleList) // If DB is not empty return DB entity
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
