package com.example.android.coroutineshomework.network

import com.example.android.coroutineshomework.dto.DtoMovie
import com.example.android.coroutineshomework.dto.DtoPopularMovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<DtoPopularMovieList>

    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path("id") id: Int): Response<DtoMovie>
}