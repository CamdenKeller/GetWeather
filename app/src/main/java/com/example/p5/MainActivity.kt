package com.example.p5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.p5.Screen.SettingsScreen.toScreen
import com.example.p5.ui.theme.P5Theme
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object HomeScreen : Screen()

    @Serializable
    data object SettingsScreen : Screen()

    @Serializable
    data class ProfileScreen(val userId: String) : Screen()

    fun NavBackStackEntry.toScreen(): Screen? =
        when (destination.route?.substringAfterLast(".")?.substringBefore("/")) {
            "HomeScreen" -> toRoute<HomeScreen>()
            "SettingsScreen" -> toRoute<SettingsScreen>()
            "ProfileScreen" -> toRoute<ProfileScreen>()
            else -> null
        }
}

data class NavItem(
    val screen: Screen,
    val label: String,
    val icon: ImageVector
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val tabs = listOf(
                NavItem(
                    label = "Real",
                    icon = cloudOn(),
                    screen = Screen.HomeScreen,
                ),
                NavItem(
                    label = "Adjustable",
                    icon = cloudOff(),
                    screen = Screen.SettingsScreen,
                )
            )
            P5Theme {
                val navController = rememberNavController()
                val navBackStackEntry = navController.currentBackStackEntryAsState().value

                Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
                    NavigationBar {
                        tabs.map { item ->
                            NavigationBarItem(
                                selected = item.screen == navBackStackEntry?.toScreen(),
                                onClick = {
                                    navController.navigate(item.screen)
                                },
                                icon = { Icon(imageVector = item.icon, contentDescription = null) },
                                label = { Text(text = item.label) }
                            )
                        }
                    }
                }) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.HomeScreen
                        ) {
                            composable<Screen.HomeScreen> {
                                Box(modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.LightGray))
                                {
                                    Networking()
                                }
                            }
                            composable<Screen.SettingsScreen> {
                                Adjustable()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun cloudOn(): ImageVector {
    return ImageVector.vectorResource(id = R.drawable.baseline_cloud_done_24)
}

@Composable
fun cloudOff(): ImageVector {
    return ImageVector.vectorResource(id = R.drawable.baseline_cloud_off_24)
}
