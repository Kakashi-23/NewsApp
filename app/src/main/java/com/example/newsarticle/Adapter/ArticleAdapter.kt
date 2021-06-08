package com.example.newsarticle.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsarticle.Model.NewsData
import com.example.newsarticle.R


class ArticleAdapter(val context: Context): RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
   private lateinit var list:ArrayList<NewsData.Articles>
   private lateinit var callback:MainActivityCallback
    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        var card:CardView=view.findViewById(R.id.card)
        var newsImage:ImageView=view.findViewById(R.id.newsImage)
        var newsTitle:TextView=view.findViewById(R.id.newsTitle)
        var newsDesc:TextView=view.findViewById(R.id.newsDesc)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_article_card,parent,false)
    return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article=list[position]
        Glide.with(context).load(article.newsImageUrl).into(holder.newsImage)
        holder.newsTitle.text = article.newsTitle
        holder.newsDesc.text = article.newsDescription
        holder.card.setOnClickListener {
            callback.openWebView(article.newsUrl)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(article :ArrayList<NewsData.Articles>){
        this.list=article
    }
    fun setCallback(call:MainActivityCallback){
        this.callback=call
    }
    interface MainActivityCallback{
        fun openWebView(url:String)
    }
}
