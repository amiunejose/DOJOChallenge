package com.example.dojochallenge.presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dojochallenge.R
import com.example.dojochallenge.presentation.layouts.Text_h2
import com.example.dojochallenge.presentation.layouts.Text_h5

@Composable
fun BiographySection(biography: String) {
    Box(modifier = Modifier.fillMaxWidth().padding(40.dp)){
        Column {
            Text_h2("Biography")
            Spacer(modifier = Modifier.height(10.dp))
            Text_h5(biography)}
    }
}

@Preview
@Composable
fun BiographySectionPreview(){
    val context = LocalContext.current
    BiographySection( context.getString(R.string.lorem_ipsum) )
}
