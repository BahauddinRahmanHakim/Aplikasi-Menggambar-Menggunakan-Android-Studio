package com.example.arted

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.arted.ui.theme.ArtedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            ArtedApp()
            ArtedTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "opening") {
                        composable("opening") {
                            OpeningScreen(onNavigateToMain = { navController.navigate("main") })
                        }
                        composable("main") {
                            MainScreen(onNavigateToSubpage = { navController.navigate("subpage") })
                        }
                        composable("subpage") {
                            SubpageScreen(onNavigateToMain = { navController.navigate("main") })
                        }
                    }
                }
            }
        }
    }
}