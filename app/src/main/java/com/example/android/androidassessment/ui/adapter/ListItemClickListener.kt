package com.example.android.androidassessment.ui.adapter

/**
 * Handles click events
 */
class ListItemClickListener(
    val clickListener: (
        chapterName: String,
        lessonName: String,
        lessonMediaUrl: String
    ) -> Unit
) {

    fun onClick(
        chapterName: String,
        lessonName: String,
        lessonMediaUrl: String
    ) = clickListener(chapterName, lessonName, lessonMediaUrl)
}