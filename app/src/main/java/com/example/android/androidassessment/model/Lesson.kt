package com.example.android.androidassessment.model

import com.squareup.moshi.Json

data class Lesson(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "icon")
    val icon: String,
    @Json(name = "media_url")
    val mediaUrl: String,
    @Json(name = "subject_id")
    val subjectId: Int,
    @Json(name = "chapter_id")
    val chapterId: Int
)