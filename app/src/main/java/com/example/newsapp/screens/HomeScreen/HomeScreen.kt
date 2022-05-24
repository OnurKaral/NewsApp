package com.example.newsapp.screens.HomeScreen


import androidx.compose.foundation.background
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.components.Reports

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = hiltViewModel(), navController: NavController){
   // val navController = rememberNavController()
    Scaffold(topBar = {
        SmallTopAppBar(title = { Text(text = "News List") },
            modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary))
    }) {
        Reports(viewModel, navController = navController)
    }
}



