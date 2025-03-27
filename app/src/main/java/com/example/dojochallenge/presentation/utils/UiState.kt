package com.example.dojochallenge.presentation.utils

import androidx.compose.runtime.Stable

@Stable
internal sealed interface UIState {

    data object Idle : UIState

    data object Loading : UIState

    data class Error(val message: String?) : UIState
}