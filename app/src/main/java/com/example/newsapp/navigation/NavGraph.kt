package com.example.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsapp.screens.DetailsScreen.DetailScreen
import com.example.newsapp.screens.HomeScreen.HomeScreen
import com.example.newsapp.screens.HomeScreen.NewsHomePage

@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screens.Home.route){
        composable(route = Screens.Home.route){
            HomeScreen()
        }
        composable(route = Screens.Detail.route){
            DetailScreen()
        }
    }
}