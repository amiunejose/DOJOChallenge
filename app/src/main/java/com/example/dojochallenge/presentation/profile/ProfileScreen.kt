package com.example.dojochallenge.presentation.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dojochallenge.presentation.layouts.TMDBHeader

@Composable
fun ProfileScreen(
    profileScreenViewModel: ProfileScreenViewModel = hiltViewModel()
) {
    val uiState by profileScreenViewModel.uiState.collectAsStateWithLifecycle()
    val popularPeopleData by profileScreenViewModel.popularPeopleData.collectAsStateWithLifecycle()

    LazyColumn() {
        item { TMDBHeader("Most Popular") }
        item { ProfileMainSection(popularPeopleData) }
        item { HorizontalDivider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(horizontal = 15.dp)) }
        item { KnownForCarrousel(popularPeopleData.knownFor) }
        item { HorizontalDivider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(horizontal = 15.dp)) }
        item { BiographySection(popularPeopleData.biography) }


    }
}
