package com.rakamin.mandirinewsproject

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("everything?country=id&apiKey=441d78c6b244400986b7e1b93a246471")
    fun getEverythingNews(): Call<News>

    @GET("top-headlines?country=id&apiKey=441d78c6b244400986b7e1b93a246471")
    fun getHeadlineNews(): Call<News>

}