package com.example.newsarticle

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsarticle.Adapter.ArticleAdapter
import com.example.newsarticle.ViewModel.ViewModel


class MainActivity : AppCompatActivity(), ArticleAdapter.MainActivityCallback {
    lateinit var recyclerView: RecyclerView
    lateinit var articleAdapter: ArticleAdapter
    lateinit var viewmodel: ViewModel
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.INTERNET)!=PackageManager.PERMISSION_GRANTED){
            showData()
        }else{
            ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.INTERNET),123)
        }


    }

    fun getData(){
        viewmodel = ViewModelProviders.of(this).get(ViewModel::class.java)
        viewmodel.getArticlesFromRepository()!!.observe(this, Observer {
            articleAdapter.setList(it!!)
            recyclerView.adapter=articleAdapter
            articleAdapter.notifyDataSetChanged()
        })

    }

    override fun openWebView(url: String) {
        val intent = Intent(this,Webview::class.java)
        intent.putExtra("Url",url)
        startActivity(intent)

    }
    fun showData(){
        if (checkConnection()){
            recyclerView = findViewById(R.id.recyclerView)
            articleAdapter = ArticleAdapter(this)
            recyclerView.layoutManager = LinearLayoutManager(this)
            articleAdapter.setCallback(this)
            getData()
        }
        else{
            Toast.makeText(this,"No internet connection",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==123){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                showData()
            }else{
                this.finishAffinity()
            }
        }
    }

    fun checkConnection():Boolean{
        var connection=false;
        val manager:ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
       val info= manager.activeNetworkInfo
        if (info!=null && info.isConnected){
            connection=true
        }
        return connection
    }
}