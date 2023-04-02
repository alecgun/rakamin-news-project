package com.rakamin.mandirinewsproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()
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

    }

    private fun printLog(message: String){
        Log.d(TAG, message)
    }

    private fun showData(data: News) {
//        mainAdapter.setData(photos)
    }

}