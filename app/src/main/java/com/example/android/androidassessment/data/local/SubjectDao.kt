package com.example.android.androidassessment.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SubjectDao {

    /**
     * Insert subjects into the table
     */
    @Insert
    fun insert(subjects: List<SubjectWithChapters>) {
        subjects.forEach { subjectWithChapter ->
            insertSubject(subjectWithChapter.subjectDb)
            subjectWithChapter.chapters.forEach { chapterWithLessons ->
                chapterWithLessons.chapterDb.subjectId = subjectWithChapter.subjectDb.id
                insertChapter(chapterWithLessons.chapterDb)
                insertLessons(chapterWithLessons.lessons)
            }
        }
    }

    /**
     * Clear table
     */
    private fun clearAllSubjects() {
        deleteSubjects()
        deleteChapters()
        deleteLessons()
    }

    /**
     * Clear all data in table and repopulate with latest data
     */
    @Transaction
    open fun clearAndInsertSubjects(subjects: List<SubjectWithChapters>) {
        clearAllSubjects()
        insert(subjects)
    }

    @Insert
    abstract fun insertLessons(lessonsDb: List<LessonDb>)

    @Insert
    abstract fun insertChapter(chapterDb: ChapterDb)

    @Insert
    abstract fun insertSubject(subjectDb: SubjectDb)

    @Query("DELETE FROM lesson")
    abstract fun deleteLessons()

    @Query("DELETE FROM chapter")
    abstract fun deleteChapters()

    @Query("DELETE FROM subject")
    abstract fun deleteSubjects()

    /**
     * Get all subjects from table
     */
    @Transaction
    @Query("SELECT * FROM subject")
    abstract fun getSubjectWithChapters(): Flow<List<SubjectWithChapters>>
}