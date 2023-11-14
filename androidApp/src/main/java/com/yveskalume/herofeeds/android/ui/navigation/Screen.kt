package com.yveskalume.herofeeds.android.ui.navigation

import com.yveskalume.herofeeds.android.ui.navigation.Screen.Profile

sealed class Screen(val route: String) {
     data object Home : Screen("home")
     data object Profile : Screen("profile/{creatorId}") {
         fun createRoute(creatorId: Long): String {
             return "profile/$creatorId"
         }
     }
     data object AddCreator: Screen("add-creator")
}

