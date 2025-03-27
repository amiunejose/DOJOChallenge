package com.example.dojochallenge.presentation.layouts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun TextLong(text: String, modifier: Modifier = Modifier, textAlign: TextAlign = TextAlign.Start) {
    Text(
    modifier = modifier,
    text = text,
    fontSize = 12.sp,
    textAlign = textAlign,
    fontWeight = FontWeight.Normal
    )
}