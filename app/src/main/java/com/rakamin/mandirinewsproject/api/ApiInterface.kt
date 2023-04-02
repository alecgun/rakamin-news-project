package com.rakamin.mandirinewsproject.api

import com.rakamin.mandirinewsproject.everythingnews.EverythingNews
import com.rakamin.mandirinewsproject.headlinenews.HeadlineNews
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("everything?q=bitcoin&apiKey=441d78c6b244400986b7e1b93a246471")
    fun getEverythingNews(): Call<EverythingNews>

    @GET("top-headlines?country=us&apiKey=441d78c6b244400986b7e1b93a246471")
    fun getHeadlineNews(): Call<HeadlineNews>

}