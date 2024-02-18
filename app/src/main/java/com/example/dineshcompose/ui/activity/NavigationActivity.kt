package com.example.dineshcompose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dineshcompose.model.BadgeBottomMenu
import com.example.dineshcompose.ui.activity.ui.theme.ButtonBlue
import com.example.dineshcompose.ui.activity.ui.theme.DeepBlue
import com.example.dineshcompose.ui.activity.ui.theme.DineshComposeTheme

class NavigationActivity : ComponentActivity() {
    private fun getBadgeBottomItems(): List<BadgeBottomMenu> {
        val list = ArrayList<BadgeBottomMenu>()
        list.add(BadgeBottomMenu("Home", "home", Icons.Default.Home))
        list.add(BadgeBottomMenu("Notification", "notification", Icons.Default.Notifications, 10))
        list.add(BadgeBottomMenu("Settings", "settings", Icons.Default.Settings))
        return list
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DineshComposeTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BadgeBottomNavigationBar(
                            badgeNavItems = getBadgeBottomItems(),
                            navController = navController,
                            modifier = Modifier,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) { padding ->
                    BadgeNavigation(
                        navHostController = navController,
                        modifier = Modifier.padding(padding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BadgeBottomNavigationBar(
    badgeNavItems: List<BadgeBottomMenu>,
    navController: NavController,
    modifier: Modifier,
    onItemClick: (BadgeBottomMenu) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = ButtonBlue,
        elevation = 5.dp
    ) {
        badgeNavItems.forEach {
            val selected = it.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(it) },
                selectedContentColor = Color.White,
                unselectedContentColor = DeepBlue,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (it.badgeCount > 0) {
                            BadgedBox(badge = {
                                Text(
                                    text = it.badgeCount.toString(),
                                    color = DeepBlue,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            ) {
                                Icon(
                                    imageVector = it.icon,
                                    contentDescription = it.title,
                                )
                            }
                        } else {
                            Icon(
                                imageVector = it.icon,
                                contentDescription = it.title
                            )
                        }
                        if (selected) {
                            Text(
                                text = it.title,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp,
                                color = DeepBlue
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun BadgeNavigation(navHostController: NavHostController, modifier: Modifier) {
    NavHost(navController = navHostController, startDestination = "home", modifier = modifier) {
        composable("home") {
            NavHomeScreen()
        }
        composable("notification") {
            NavNotificationScreen()
        }
        composable("settings") {
            NavSettingsScreen()
        }

    }
}

@Composable
fun NavHomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "NavHomeScreen")
    }
}

@Composable
fun NavNotificationScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "NavNotificationScreen")
    }
}

@Composable
fun NavSettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "NavSettingsScreen")
    }
}