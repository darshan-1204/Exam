package com.example.exam.Api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("top-headlines")
    fun getData(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey:String
    ) : retrofit2.Call<NewsModel>
}