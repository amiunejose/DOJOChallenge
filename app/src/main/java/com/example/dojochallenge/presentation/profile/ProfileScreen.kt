package com.example.dojochallenge.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dojochallenge.presentation.layouts.TMDBHeader

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by profileViewModel.uiState.collectAsStateWithLifecycle()
    val popularPeopleData by profileViewModel.popularPeopleData.collectAsStateWithLifecycle()

    Column() {
        TMDBHeader("Most Popular", "") { }
        ProfileMainSection(
            popularPeopleData.fullProfilePathImageW600xH900, popularPeopleData.name, popularPeopleData.knowForDepartment,
            popularPeopleData.gender
        )
        KnownForCarrousel(popularPeopleData.knownFor)
    }
}
