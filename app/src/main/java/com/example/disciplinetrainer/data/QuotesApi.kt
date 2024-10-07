package com.example.disciplinetrainer.data

import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesApi {
    @GET("v1/quotes?")
    suspend fun getQuotes(@Query("page") quoteType: String): List<Quote>
}