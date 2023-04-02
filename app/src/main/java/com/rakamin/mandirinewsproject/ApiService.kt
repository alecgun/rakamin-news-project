package com.rakamin.mandirinewsproject

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiService {

    val BASE_URL : String = "https://newsapi.org/v2/"
    val endpoint : ApiInterface
        get() {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
}