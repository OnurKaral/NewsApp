package com.example.newsapp.repository


import com.example.newsapp.data.DataOrException
import com.example.newsapp.model.Article
import com.example.newsapp.network.NewsApi
import javax.inject.Inject

class NewsRepository @Inject  constructor(
    private val api: NewsApi) {
    private val dataOrException = DataOrException<List<Article>, Boolean, Exception>()

    suspend fun getNews(): DataOrException<List<Article>, Boolean, Exception> {

        try {

            dataOrException.isLoading  = true
            dataOrException.data = api.getNews("us","4635ce667e294e1fabdc1dabd4b7f9af").articles
           if (dataOrException.data.toString().isNotEmpty()) dataOrException.isLoading = false


        }catch (exception: Exception){
            dataOrException.exception = exception
        }
        return dataOrException
}
}