package com.example.dojochallenge.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dojochallenge.data.model.TMDBMovieModel
import com.example.dojochallenge.domain.repository.TMDBMoviesRepository
import com.example.dojochallenge.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MoviesScreenViewModel @Inject constructor(private val moviesRepository: TMDBMoviesRepository) : ViewModel() {

    internal val popularMoviesUiState: MutableStateFlow<UIState> = MutableStateFlow(UIState.Loading)
    internal val topRatedMoviesUiState: MutableStateFlow<UIState> = MutableStateFlow(UIState.Loading)
    internal val nowPlayingMoviesUiState: MutableStateFlow<UIState> = MutableStateFlow(UIState.Loading)

    private val popularMoviesDataFetcher: MutableStateFlow<List<TMDBMovieModel>> = MutableStateFlow(emptyList() )
    val popularMoviesList: StateFlow<List<TMDBMovieModel>> = popularMoviesDataFetcher.flatMapLatest {
        moviesRepository.fetchPopularMovieList(
            onStart = { popularMoviesUiState.tryEmit(UIState.Loading) },
            onComplete = { popularMoviesUiState.tryEmit(UIState.Idle) },
            onError = { popularMoviesUiState.tryEmit(UIState.Error(it)) }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    private val topRatedMoviesDataFetcher: MutableStateFlow<List<TMDBMovieModel>> = MutableStateFlow(emptyList() )
    val topRatedMoviesList: StateFlow<List<TMDBMovieModel>> = topRatedMoviesDataFetcher.flatMapLatest {
        moviesRepository.fetchTopRatedMovieList(
            onStart = { topRatedMoviesUiState.tryEmit(UIState.Loading) },
            onComplete = { topRatedMoviesUiState.tryEmit(UIState.Idle) },
            onError = { topRatedMoviesUiState.tryEmit(UIState.Error(it)) }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    private val nowPlayingMoviesDataFetcher: MutableStateFlow<List<TMDBMovieModel>> = MutableStateFlow(emptyList() )
    val nowPlayingMoviesList: StateFlow<List<TMDBMovieModel>> = nowPlayingMoviesDataFetcher.flatMapLatest {
        moviesRepository.fetchNowPlayingMovieList(
            onStart = { nowPlayingMoviesUiState.tryEmit(UIState.Loading) },
            onComplete = { nowPlayingMoviesUiState.tryEmit(UIState.Idle) },
            onError = { nowPlayingMoviesUiState.tryEmit(UIState.Error(it)) }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

}
