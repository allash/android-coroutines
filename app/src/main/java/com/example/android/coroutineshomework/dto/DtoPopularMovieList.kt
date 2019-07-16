package com.example.android.coroutineshomework.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class DtoPopularMovieList (
    val page: Int?,
    val results: List<DtoMovie>?,
    @field:Json(name = "total_results")
    val totalResults: Int?,
    @field:Json(name = "total_page")
    val totalPage: String?
): Parcelable