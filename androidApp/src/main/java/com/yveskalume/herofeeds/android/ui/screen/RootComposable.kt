package com.yveskalume.herofeeds.android.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yveskalume.herofeeds.android.ui.navigation.Screen
import com.yveskalume.herofeeds.android.ui.screen.addcreator.AddCreatorRoute
import com.yveskalume.herofeeds.android.ui.screen.home.HomeRoute
import com.yveskalume.herofeeds.android.ui.screen.profile.ProfileRoute

@Composable
fun RootComposable() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeRoute(
                onCreatorClick = { id ->
                    navController.navigate(
                        Screen.Profile.createRoute(id)
                    )
                },
                onAddClick = { navController.navigate(Screen.AddCreator.route) }
            )
        }

        composable(
            Screen.Profile.route,
            arguments = listOf(
                navArgument("creatorId") { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val creatorId = backStackEntry.arguments?.getLong("creatorId")
            ProfileRoute(
                creatorId = creatorId,
                onNavigateBack = { navController.navigateUp() }
            )
        }

        composable(Screen.AddCreator.route) {
            AddCreatorRoute(
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}