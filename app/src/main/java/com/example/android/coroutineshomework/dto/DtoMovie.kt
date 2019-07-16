package com.example.android.coroutineshomework.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class DtoMovie(
    @field:Json(name = "poster_path")
    val posterPath: String?,
    val adult: Boolean?,
    val overview: String?,
    @field:Json(name = "release_date")
    val releaseDate: String?,
    @field:Json(name = "genre_ids")
    val genreIds: List<Int>?,
    val id: String?,
    @field:Json(name = "original_title")
    val originalTitle: String?,
    @field:Json(name = "original_language")
    val originalLanguage: String?,
    val title: String?,
    @field:Json(name = "backdrop_path")
    val backdropPath: String?,
    val popularity: String?, // number
    @field:Json(name = "vote_count")
    val voteCount: Int?,
    val video: Boolean?,
    @field:Json(name = "vote_average")
    val voteAverage: String? // number
) : Parcelable


