package com.rakamin.mandirinewsproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rakamin.mandirinewsproject.api.ApiService
import com.rakamin.mandirinewsproject.databinding.ActivityMainBinding
import com.rakamin.mandirinewsproject.everythingnews.EverythingNews
import com.rakamin.mandirinewsproject.everythingnews.MainAdapter
import com.rakamin.mandirinewsproject.headlinenews.HeadlineAdapter
import com.rakamin.mandirinewsproject.headlinenews.HeadlineNews
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
            .enqueue(object : Callback<EverythingNews> {
                override fun onResponse(
                    call: Call<EverythingNews>,
                    response: Response<EverythingNews>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        showEverythingData(result!!)
                    }
                }

                override fun onFailure(call: Call<EverythingNews>, t: Throwable) {
                    printLog(t.toString())
                }

            })

        ApiService.endpoint.getHeadlineNews()
            .enqueue(object : Callback<HeadlineNews> {
                override fun onResponse(
                    call: Call<HeadlineNews>,
                    response: Response<HeadlineNews>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        showHeadlineData(result!!)
                    }
                }

                override fun onFailure(call: Call<HeadlineNews>, t: Throwable) {
                    printLog(t.toString())
                }

            })

    }

    private fun printLog(message: String){
        Log.d(TAG, message)
    }

    private fun showEverythingData(everynews: EverythingNews) {
        val articles = everynews.articles
        mainAdapter.setData(articles)
        for (item in articles) {
            printLog( "title: ${item.title}" )
            printLog( "urlToImage: ${item.urlToImage}" )
        }
    }

    private fun showHeadlineData(headlinenews: HeadlineNews) {
        val articles = headlinenews.articles
        headlineAdapter.setData(articles)
        for (item in articles) {
            printLog( "title: ${item.title}" )
            printLog( "urlToImage: ${item.urlToImage}" )
        }
    }

}