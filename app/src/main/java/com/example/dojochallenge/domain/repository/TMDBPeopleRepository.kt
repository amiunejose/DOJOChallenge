package com.example.dojochallenge.domain.repository

import com.example.dojochallenge.data.model.TMDBPopularPeopleModel
import kotlinx.coroutines.flow.Flow

interface TMDBPeopleRepository {

    fun fetchPopularPeopleList(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<TMDBPopularPeopleModel>>

    fun fetchMostPopularPeople(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<TMDBPopularPeopleModel>
}
