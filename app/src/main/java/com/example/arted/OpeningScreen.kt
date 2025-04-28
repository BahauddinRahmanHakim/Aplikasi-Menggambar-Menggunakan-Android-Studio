package com.example.arted

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun OpeningScreen(onNavigateToMain: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.opening_background), // Replace with your image resource
            contentDescription = "Opening Screen Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Or ContentScale.FillBounds, ContentScale.Fit, etc.
        )

        // Content (Buttons, Text, etc.)
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(onClick = {
                onNavigateToMain()
            }) {
                Text("Start Drawing")
            }
        }
    }
}