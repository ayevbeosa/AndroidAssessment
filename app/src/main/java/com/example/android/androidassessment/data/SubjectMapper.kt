package com.example.android.androidassessment.data

import com.example.android.androidassessment.data.local.*
import com.example.android.androidassessment.data.utils.Mapper
import com.example.android.androidassessment.model.Chapter
import com.example.android.androidassessment.model.Lesson
import com.example.android.androidassessment.model.Subject

interface SubjectMapper : Mapper<SubjectWithChapters, Subject> {
    override fun SubjectWithChapters.toRemote(): Subject {
        return Subject(
            id = subjectDb.id,
            name = subjectDb.name,
            icon = subjectDb.icon,
            chapters = chapters.map {
                Chapter(
                    id = it.chapterDb.id,
                    name = it.chapterDb.name,
                    lessons = it.lessons.map { lesson ->
                        Lesson(
                            id = lesson.lessonId,
                            name = lesson.name,
                            icon = lesson.icon,
                            mediaUrl = lesson.mediaUrl,
                            subjectId = lesson.subjectId,
                            chapterId = lesson.chapterId,
                        )
                    },
                )
            }
        )
    }

    override fun Subject.toLocal(): SubjectWithChapters {
        return SubjectWithChapters(
            subjectDb = SubjectDb(
                id = id,
                name = name,
                icon = icon
            ),
            chapters = chapters.map { chapter ->
                ChapterWithLessons(
                    chapterDb = ChapterDb(
                        id =  chapter.id,
                        name = chapter.name,
                        subjectId = id
                    ),
                    lessons = chapter.lessons.map { lesson ->
                        LessonDb(
                            lessonId = lesson.id,
                            name = lesson.name,
                            icon = lesson.icon,
                            mediaUrl = lesson.mediaUrl,
                            subjectId = lesson.subjectId,
                            chapterId = lesson.chapterId
                        )
                    }
                )
            }
        )
    }
}