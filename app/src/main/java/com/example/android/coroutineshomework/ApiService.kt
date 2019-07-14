package com.example.android.coroutineshomework

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/adrien?format=json")
    suspend fun getTracks(): Response<List<Track>>
}