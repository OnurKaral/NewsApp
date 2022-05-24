package com.example.newsapp.screens.DetailsScreen

import android.telecom.Call.Details
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun DetailScreen(){


    AndroidView(factory = {
        WebView(it).apply {
            webViewClient = WebViewClient()

            //loadUrl(url)
        }
    })
}