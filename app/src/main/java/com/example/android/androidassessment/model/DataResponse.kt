package com.example.android.androidassessment.model

import com.squareup.moshi.Json

data class DataResponse(
    @Json(name = "data")
    val `data`: Data
)