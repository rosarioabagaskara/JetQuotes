package com.rosariobagaskara.jetquotes

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://random-quote-api1.p.rapidapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(Api::class.java)
    }
}