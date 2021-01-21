package com.example.android.androidassessment.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubjectNavigation(
    val chapterName: String,
    val lessonName: String,
    val lessonMediaUrl: String
) : Parcelable
