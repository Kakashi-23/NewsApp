package com.example.newsarticle.Service

import com.example.newsarticle.Model.NewsData
import retrofit2.Call
import retrofit2.http.GET


interface Api {

    @GET("everything?q=tesla&from=2021-05-08&sortBy=publishedAt&apiKey=5a521276eb2a4a2793c702a4da229a97")
    fun getNews():Call<NewsData>
}