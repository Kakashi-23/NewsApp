package com.example.newsarticle.Model

import com.google.gson.annotations.SerializedName

data class NewsData(
@SerializedName("status") var status:String,
@SerializedName("articles") var articles:ArrayList<Articles>
) {
    data class Articles(
        @SerializedName("title") var newsTitle:String,
        @SerializedName("description") var newsDescription:String,
        @SerializedName("url") var newsUrl:String,
        @SerializedName("urlToImage") var newsImageUrl:String,
    )
}