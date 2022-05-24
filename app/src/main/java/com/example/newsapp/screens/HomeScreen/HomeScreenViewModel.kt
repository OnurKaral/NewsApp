package com.example.newsapp.screens.HomeScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.DataOrException
import com.example.newsapp.model.Article
import com.example.newsapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val newsDB: NewsRepository): ViewModel() {
    val data: MutableState<DataOrException<List<Article>,
            Boolean, Exception>> = mutableStateOf(DataOrException(null, true, Exception("")))

    init {
        getAllQuestions()
    }

    private fun getAllQuestions(){
        viewModelScope.launch {
            data.value.isLoading = true
            data.value = newsDB.getNews()
            if(data.value.data.toString().isNotEmpty()){
                data.value.isLoading = false
            }
        }
    }
}