package com.example.android.androidassessment.utils

import com.example.android.androidassessment.data.local.*

val lessonListOne = listOf(
    LessonDb(1, "LessonNameOne", "IconOne", "Url1", 1, 1)
)

val lessonListTwo = listOf(
    LessonDb(2, "LessonNameTwo", "IconTwo", "Url2", 2, 2)
)

// Dummy ChapterWithLessons Lists
val chapterListOne = listOf(
    ChapterWithLessons(
        ChapterDb(1, "ChapterName1", 1),
        lessonListOne
    )
)

val chapterListTwo = listOf(
    ChapterWithLessons(
        ChapterDb(2, "ChapterName2", 2),
        lessonListTwo
    )
)

val dummyData = listOf(
    SubjectWithChapters(
        SubjectDb(1, "Name1", "Icon1"),
        chapterListOne
    ),
    SubjectWithChapters(
        SubjectDb(2, "Name2", "Icon2"),
        chapterListTwo
    )
)