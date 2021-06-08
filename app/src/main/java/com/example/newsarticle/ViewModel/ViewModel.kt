package com.example.newsarticle.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsarticle.Model.NewsData
import com.example.newsarticle.Repository.Repository

class ViewModel():ViewModel() {
    fun getArticlesFromRepository(): MutableLiveData<ArrayList<NewsData.Articles>>{
        val repository= Repository()
      return repository.getData()
    }
}