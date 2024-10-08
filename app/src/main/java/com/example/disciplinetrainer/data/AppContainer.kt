package com.example.disciplinetrainer.data

import android.app.Application
import com.example.disciplinetrainer.data.network.AuthInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class AppContainer: Application() {
    private val baseUrl = "https://api.api-ninjas.com/"
    private val token = "Z0vhHTWIJdQpEosIXlrSfA==joz7qx9cco0axx5y"
    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(token)).build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .client(client)
        .build()

    private val quotesService: QuotesApi by lazy{
        retrofit.create(QuotesApi::class.java)
    }

    suspend fun getQuotes(): List<Quote>{
        return quotesService.getQuotes("success")
    }
}