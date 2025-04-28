package com.example.arted

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// 1. Define Routes as Constants or Enums
//    - This improves type safety and reduces the risk of typos.
//    - It also makes it easier to refactor routes later.
sealed class Screen(val route: String) {
    data object Opening : Screen("opening")
    data object Main : Screen("main")
    data object Subpage : Screen("subpage")
}

@Composable
fun ArtedApp() {
    // 2. Use rememberNavController() only once.
    //    - It's good practice to create the NavController at the top level of your navigation hierarchy.
    val navController = rememberNavController()

    AppNavHost(navController = navController)
}

@Composable
fun AppNavHost(navController: NavHostController) {
    // 3. Use the defined Screen routes in the NavHost.
    NavHost(navController = navController, startDestination = Screen.Opening.route) {
        composable(Screen.Opening.route) {
            OpeningScreen(onNavigateToMain = {
                // 4. Use the Screen object to navigate.
                navController.navigate(Screen.Main.route)
            })
        }
        composable(Screen.Main.route) {
            // 5. Pass only what is needed.
            //    - In this case, MainScreen doesn't need the whole navController.
            //    - If it needs to navigate, pass a lambda function.
            MainScreen(onNavigateToSubpage = {
                navController.navigate(Screen.Subpage.route)
            })
        }
        composable(Screen.Subpage.route) {
            SubPageScreen(onNavigateToMain = {
                navController.navigate(Screen.Main.route)
            })
        }
    }
}