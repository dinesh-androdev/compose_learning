package com.example.dineshcompose.navigation

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dineshcompose.ui.designs.DropDown
import com.example.dineshcompose.ui.designs.HomeScreen

@Composable
fun MainNavigation(context: Context) {
    val navController =  rememberNavController()
    NavHost(navController = navController,
        startDestination = Screen.HomeScreen.rout){
        composable(route = Screen.HomeScreen.rout){
            HomeScreen(navController = navController, context = context)
        }
        composable(
            route = Screen.DetailsScreen.rout + "/{currentMeditation}",
            arguments = listOf(
                navArgument("currentMeditation"){
                    type = NavType.StringType
                    defaultValue = "Dinesh"
                    nullable = true
                }
            )
        ){entry ->
            DropDown(
                text = entry.arguments?.getString("currentMeditation")?:"",
                modifier = Modifier.padding(15.dp)
            )
        }
    }
}