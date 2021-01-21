package com.example.android.androidassessment.data.local

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "subject")
data class SubjectDb(
    @PrimaryKey val id: Int,
    val name: String,
    val icon: String,
) : Parcelable

@Parcelize
@Entity(tableName = "chapter")
data class ChapterDb(
    @PrimaryKey val id: Int,
    val name: String,
    var subjectId: Int,
) : Parcelable

@Parcelize
@Entity(tableName = "lesson")
data class LessonDb(
    @PrimaryKey val lessonId: Int,
    val name: String,
    val icon: String,
    val mediaUrl: String,
    val subjectId: Int,
    val chapterId: Int
) : Parcelable

@Parcelize
data class SubjectWithChapters(
    @Embedded val subjectDb: SubjectDb,
    @Relation(
        parentColumn = "id",
        entityColumn = "subjectId",
        entity = ChapterDb::class
    )
    val chapters: List<ChapterWithLessons>,
) : Parcelable

@Parcelize
data class ChapterWithLessons(
    @Embedded val chapterDb: ChapterDb,
    @Relation(
        parentColumn = "id",
        entityColumn = "chapterId",
        entity = LessonDb::class
    )
    val lessons: List<LessonDb>,
) : Parcelable