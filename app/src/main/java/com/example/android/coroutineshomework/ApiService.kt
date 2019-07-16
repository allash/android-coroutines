package com.example.android.coroutineshomework

import retrofit2.Response
import retrofit2.http.GET

// https://developers.themoviedb.org/3/movies/get-popular-movies
interface ApiService {
    @GET("/adrien")
    suspend fun getTracks(): Response<List<Track>>
}