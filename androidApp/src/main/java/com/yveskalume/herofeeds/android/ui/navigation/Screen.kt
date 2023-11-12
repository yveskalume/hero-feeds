package com.yveskalume.herofeeds.android.ui.navigation

import com.yveskalume.herofeeds.android.ui.navigation.Screen.Profile

sealed class Screen(val route: String) {
     data object Home : Screen("home")
     data object Profile : Screen("profile/{id}") {
         fun createRoute(id: String): String {
             return "profile/$id"
         }
     }
     data object AddCreator: Screen("add-creator")
}

