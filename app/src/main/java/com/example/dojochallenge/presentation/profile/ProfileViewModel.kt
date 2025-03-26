package com.example.dojochallenge.presentation.profile

import androidx.compose.runtime.Stable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dojochallenge.data.model.TMDBPeopleModel
import com.example.dojochallenge.domain.repository.TMDBPeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val peopleRepository: TMDBPeopleRepository) : ViewModel() {

    internal val uiState: MutableStateFlow<ProfileUIState> = MutableStateFlow(ProfileUIState.Loading)

    private val profileFetchingPeople: MutableStateFlow<TMDBPeopleModel> = MutableStateFlow(TMDBPeopleModel())
    val popularPeopleData: StateFlow<TMDBPeopleModel> = profileFetchingPeople.flatMapLatest {
        peopleRepository.fetchMostPopularPeople(
            onStart = { uiState.tryEmit(ProfileUIState.Loading) },
            onComplete = { uiState.tryEmit(ProfileUIState.Idle) },
            onError = { uiState.tryEmit(ProfileUIState.Error(it)) }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TMDBPeopleModel()
    )
}

@Stable
internal sealed interface ProfileUIState {

    data object Idle : ProfileUIState

    data object Loading : ProfileUIState

    data class Error(val message: String?) : ProfileUIState
}
