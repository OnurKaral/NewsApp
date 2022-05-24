package com.example.newsapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.model.Article
import com.example.newsapp.screens.HomeScreen.HomeScreenViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun Reports(viewModel: HomeScreenViewModel, navController: NavController){
    val news = viewModel.data.value

    news.data?.let {
        LazyColumn(){
            items(it){
                NewsCard(article = it, onClick = {
                    val encodedUrl = URLEncoder.encode(it.url, StandardCharsets.UTF_8.toString())

                    navController.navigate(route = "detail_screen/$encodedUrl")
                })
            }

        }
    }


}

@ExperimentalCoroutinesApi
@Composable
fun NewsCard(
    article: Article,
    onClick: (Article) -> Unit,
){
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
            )
            .fillMaxWidth()
            .clickable { onClick(article) },
        elevation = 8.dp,
    ) {

        Column() {
            article.urlToImage?.let { url ->
               AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                   .data(url)
                   .crossfade(true)
                   .build(), contentDescription ="Image",
                   modifier = Modifier.fillMaxWidth()  )
                }

            article.title?.let { title ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ){
                    Text(
                        text = title,
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start)
                        , fontSize = 16.sp,
                        fontWeight = FontWeight.Bold

                    )

                }
            }
            article.description?.let { description ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ){
                    Text(
                        text = description,
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start)
                        ,
                        fontSize = 14.sp , fontWeight = FontWeight.Light,

                        )

                }
            }
        }
        }
    }

