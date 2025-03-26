package com.example.dojochallenge.presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.dojochallenge.R
import com.example.dojochallenge.presentation.layouts.Text_h1
import com.example.dojochallenge.presentation.layouts.Text_h2
import com.example.dojochallenge.presentation.layouts.Text_h3
import com.example.dojochallenge.presentation.layouts.Text_h4

@Composable
fun ProfileMainSection(imageResource: String, name: String, knownForDepartment: String, gender: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.TopCenter
        ) {
            AsyncImage(
                model = imageResource,
                contentDescription = name,
                placeholder = painterResource(R.drawable.default_profile_image_300_400)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .weight(1f)
        ) {
            Text_h1(name)
            Spacer(modifier = Modifier.height(20.dp))

            Text_h2("Personal Info")
            Spacer(modifier = Modifier.height(15.dp))

            Text_h3("Known For")
            Text_h4(knownForDepartment)
            Spacer(modifier = Modifier.height(10.dp))

            Text_h3("Gender")
            Text_h4(gender)
        }
    }
}

@Preview
@Composable
fun ProfileMainSectionPreview() {
    ProfileMainSection("", "", "", "")
}
