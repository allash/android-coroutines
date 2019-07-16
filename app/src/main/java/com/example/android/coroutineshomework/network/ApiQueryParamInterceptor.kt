package com.example.android.coroutineshomework.network

import com.example.android.coroutineshomework.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiQueryParamInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url().newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()
        val request = chain.request().newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}