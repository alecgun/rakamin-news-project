package com.rakamin.mandirinewsproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rakamin.mandirinewsproject.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG: String = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    lateinit var mainAdapter: MainAdapter
    lateinit var headlineAdapter: HeadlineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()
        getDataFromApi()
        setRecyclerView()
    }

    private fun setRecyclerView(){
        mainAdapter = MainAdapter(arrayListOf())
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mainAdapter
        }

        headlineAdapter = HeadlineAdapter(arrayListOf())
        binding.rvHeadline.apply {
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            adapter = headlineAdapter
        }
    }

    private fun getDataFromApi(){
        ApiService.endpoint.getEverythingNews()
            .enqueue(object : Callback<News> {
                override fun onResponse(
                    call: Call<News>,
                    response: Response<News>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        showData(result!!)
                    }
                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    printLog(t.toString())
                }

            })

        ApiService.endpoint.getHeadlineNews()
            .enqueue(object : Callback<News> {
                override fun onResponse(
                    call: Call<News>,
                    response: Response<News>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        showData(result!!)
                    }
                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    printLog(t.toString())
                }

            })

    }

    private fun printLog(message: String){
        Log.d(TAG, message)
    }

    private fun showData(news: News) {
        val articles = news.articles
        mainAdapter.setData(articles)
        headlineAdapter.setData(articles)
        for (item in articles) {
            printLog( "title: ${item.title}" )
            printLog( "urlToImage: ${item.urlToImage}" )
        }
    }

}