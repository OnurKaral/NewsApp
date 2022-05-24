package com.example.newsapp.screens.DetailScreen

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.newsapp.navigation.Screens


@Composable
fun DetailScreen(url: String,navController: NavController){

    val urlnew = url.substringAfterLast("}, url=").replace("}]","")
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
                    })
            }
        }
    }
}