package com.example.newsapp.navigation

import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
            }
        )
        ) {
            Log.d("test",it.arguments.toString())
            DetailScreen("a101"+ it.arguments.toString(), navController)
        }
    }



}


@Composable
fun DetailScreen(url: String,navController: NavController){


    var urlnew = url.substringAfterLast("}, url=").replace("}]","")
    Scaffold(topBar = {
        SmallTopAppBar(title = { Text(text = "Details") },
            modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary),

            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate(Screens.Home.route){
                        popUpTo(Screens.Home.route){
                            inclusive= true
                        }
                    }
                }) {
                    Icon(Icons.Filled.ArrowBack, "")
                } },)
    }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
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
                        it.loadUrl(urlnew)
                    }
                )

            }

        }
    }

}