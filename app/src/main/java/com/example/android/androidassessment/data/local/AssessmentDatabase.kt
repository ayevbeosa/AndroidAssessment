package com.example.android.androidassessment.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [SubjectDb::class, ChapterDb::class, LessonDb::class],
    version = 1,
    exportSchema = false
)
abstract class AssessmentDatabase : RoomDatabase() {

    abstract fun subjectDao(): SubjectDao

    companion object {

        fun buildDatabase(context: Context): AssessmentDatabase {
               return Room.databaseBuilder(
                    context,
                    AssessmentDatabase::class.java,
                    "assessment.db"
                )
                    .build()
            }
    }
}