package com.example.android.coroutineshomework

data class Track (
    val _id: String,
    val uId: String = "",
    val uNm: String = "",
    val text: String = "",
    val pl: Pl? = null,
    val name: String = "",
    val eId: String = "",
    val ctx: String = "",
    val img: String = "",
    val src: Source? = null,
    val nbP: String = "",
    val lov: List<String> = listOf()

) {
    data class Pl (
        val name: String = "",
        val id: String = ""
    )

    data class Source (
        val id: String = "",
        val name: String = ""
    )
}

