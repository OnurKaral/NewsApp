package com.example.newsapp.navigation

import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.components.Reports
import com.example.newsapp.screens.HomeScreen.HomeScreen
import com.example.newsapp.screens.HomeScreen.HomeScreenViewModel
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

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
            }
        )
        ) {
            Log.d("test",it.arguments.toString())
            DetailScreen(it.arguments.toString(), navController)
        }
    }



}


@Composable
fun DetailScreen(url: String,navController: NavController){
    val decodeURL = "https://google.com/"


    Scaffold(topBar = {
        SmallTopAppBar(title = { Text(text = "News List") },
            modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary))
    }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {


            Column {
                AndroidView(
                    factory = {
                        WebView(it).apply {
                            webViewClient = object : WebViewClient() {
                                override fun shouldOverrideUrlLoading(
                                    view: WebView?,
                                    request: WebResourceRequest?
                                ): Boolean {
                                    return false
                                }
                            }
                        }
                    }, update = {
                        it.loadUrl(decodeURL)
                    }
                )
            }
            
            Button(onClick = {
                navController.navigate(Screens.Home.route){
                    popUpTo(Screens.Home.route){
                        inclusive= true
                    }
                }
            }, modifier = Modifier
                .padding(10.dp)
                .width(30.dp)
                .height(20.dp)
                
            ) {
                Text(text = "test")
            }
        }
    }

}