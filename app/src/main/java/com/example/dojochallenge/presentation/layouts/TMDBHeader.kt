package com.example.dojochallenge.presentation.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dojochallenge.R
import com.example.dojochallenge.ui.theme.HeaderBackgroundBlue
import com.example.dojochallenge.ui.theme.HeaderText
import com.example.dojochallenge.ui.theme.Pink80
import com.example.dojochallenge.ui.theme.Purple40

@Composable
fun TMDBHeader(title: String, previousScreen: String = "", onBackPressed: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(HeaderBackgroundBlue)
            .padding(20.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = HeaderText
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { onBackPressed() }
        ) {
            if(previousScreen.isNotBlank()){
                Icon(
                    painter = painterResource(R.drawable.back_arrow_icon),
                    contentDescription = "back to $previousScreen",
                    tint = HeaderText

                )
                Text(
                    text = previousScreen,
                    fontSize = 14.sp,
                    color = HeaderText
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewTMDBHeader() {
    TMDBHeader("{title}", "{previousScreen}") { }
}