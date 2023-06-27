package com.example.exam.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object{
        lateinit var retrofit : Retrofit
        var base_url = "https://newsapi.org/v2/"
        fun getApiClient(): Retrofit {
            retrofit = Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit
        }
    }
}