package com.yveskalume.herofeeds.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yveskalume.herofeeds.android.ui.navigation.Screen
import com.yveskalume.herofeeds.android.ui.screen.RootComposable
import com.yveskalume.herofeeds.android.ui.screen.addcreator.AddCreatorRoute
import com.yveskalume.herofeeds.android.ui.screen.home.HomeRoute
import com.yveskalume.herofeeds.android.ui.screen.profile.ProfileRoute
import com.yveskalume.herofeeds.android.ui.theme.HeroFeedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            HeroFeedTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootComposable()
                }
            }
        }
    }
}
