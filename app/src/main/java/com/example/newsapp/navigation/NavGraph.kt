package com.example.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.screens.DetailScreen.DetailScreen
import com.example.newsapp.screens.HomeScreen.HomeScreen

@Composable
fun SetupNavGraph(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(route = Screens.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screens.Detail.route + "/{url}",
        arguments = listOf(
            navArgument("url"){
                type = NavType.StringType
            })) {
            DetailScreen(it.arguments.toString(), navController)
        }
    }
}