package com.example.android.androidassessment.model

import com.squareup.moshi.Json

data class Chapter(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "lessons")
    val lessons: List<Lesson>
)