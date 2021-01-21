package com.example.android.androidassessment.model

import com.squareup.moshi.Json

data class Data(
    @Json(name = "status")
    val status: String,
    @Json(name = "message")
    val message: String,
    @Json(name = "subjects")
    val subjects: List<Subject>
)