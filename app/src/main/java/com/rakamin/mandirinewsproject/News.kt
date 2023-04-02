package com.rakamin.mandirinewsproject

import com.google.gson.annotations.SerializedName

data class News(

    val status       : String,
    val totalResults : Int,
    val articles     : ArrayList<Articles>

) {

    data class Articles (

        val source      : Source = Source(),
        val author      : String,
        val title       : String,
        val description : String,
        val url         : String,
        val urlToImage  : String,
        val publishedAt : String,
        val content     : String

    )

    data class Source (

        val id   : String? = null,
        val name : String? = null

    )

}
