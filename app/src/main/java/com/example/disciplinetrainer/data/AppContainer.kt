package com.example.disciplinetrainer.data

import android.app.Application
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class AppContainer: Application() {
    private val baseUrl = "https://api.api-ninjas.com/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val quotesService: QuotesApi by lazy{
        retrofit.create(QuotesApi::class.java)
    }
    suspend fun getQuotes(): List<Quote>{
        return quotesService.getQuotes("success")
    }
}