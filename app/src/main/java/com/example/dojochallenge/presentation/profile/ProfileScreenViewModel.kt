package com.example.dojochallenge.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dojochallenge.data.model.TMDBPopularPeopleModel
import com.example.dojochallenge.domain.repository.TMDBPeopleRepository
import com.example.dojochallenge.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(private val peopleRepository: TMDBPeopleRepository) : ViewModel() {

    internal val uiState: MutableStateFlow<UIState> = MutableStateFlow(UIState.Loading)

    private val profileDataFetcher: MutableStateFlow<TMDBPopularPeopleModel> = MutableStateFlow(TMDBPopularPeopleModel())
    val popularPeopleData: StateFlow<TMDBPopularPeopleModel> = profileDataFetcher.flatMapLatest {
        peopleRepository.fetchMostPopularPeople(
            onStart = { uiState.tryEmit(UIState.Loading) },
            onComplete = { uiState.tryEmit(UIState.Idle) },
            onError = { uiState.tryEmit(UIState.Error(it)) }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TMDBPopularPeopleModel()
    )
}
