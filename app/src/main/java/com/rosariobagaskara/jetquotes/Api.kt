package com.rosariobagaskara.jetquotes

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface Api {
    @Headers("Accept: application/json",
        "X-RapidAPI-Key: a08b363b89msh7c9c6288355db1dp160429jsne22204771605",
        "X-RapidAPI-Host: random-quote-api1.p.rapidapi.com")
    @GET("/quotes")
    fun getQuotes(): Call<ArrayList<QuotesData>>
}