package com.example.android.androidassessment.model

import com.squareup.moshi.Json

data class Subject(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "icon")
    val icon: String,
    @Json(name = "chapters")
    val chapters: List<Chapter>
)