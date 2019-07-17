package com.example.android.coroutineshomework.network

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitFactory {
    private const val BASE_URL = "https://api.themoviedb.org"
    private const val API_VERSION = "3"

    fun build(): ApiService {

        val httpInterceptor = HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(ApiQueryParamInterceptor())
            .addInterceptor(httpInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("$BASE_URL/$API_VERSION/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .build()
            .create(ApiService::class.java)
    }
}