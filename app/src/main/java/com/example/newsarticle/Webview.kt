package com.example.newsarticle

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity


class Webview : AppCompatActivity() {
    lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val url = intent.getStringExtra("Url")
        webView = findViewById(R.id.webView)
        if (url!=null){
            webView.loadUrl(url)
        }else{
            webView.loadUrl("google.com")
        }

    }

}