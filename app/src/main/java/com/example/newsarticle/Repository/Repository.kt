package com.example.newsarticle.Repository

import androidx.lifecycle.MutableLiveData
import com.example.newsarticle.Model.NewsData
import com.example.newsarticle.Service.Api
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class Repository() {
    var newsData = MutableLiveData<ArrayList<NewsData.Articles>>()
    fun getArticles(){

        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(Api::class.java)
        val call = api.getNews()
        call.enqueue(object : Callback<NewsData> {
            override fun onResponse(call: Call<NewsData>, response: Response<NewsData>) {
                if (response.isSuccessful){
                    if (response.body()!=null){
                        newsData.value=response.body()!!.articles
                    }

                }
            }

            override fun onFailure(call: Call<NewsData>, t: Throwable) {

            }
        })

    }
    fun getData():MutableLiveData<ArrayList<NewsData.Articles>>{
        getArticles()
        return newsData
    }
}