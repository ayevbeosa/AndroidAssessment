package com.example.android.androidassessment.data.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.androidassessment.utils.DaoTest
import com.example.android.androidassessment.utils.assertItems
import com.example.android.androidassessment.utils.dummyData
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SubjectDaoTest : DaoTest<AssessmentDatabase>(AssessmentDatabase::class.java) {

    @Test
    @Throws(InterruptedException::class)
    fun insertSubjects() {
        // ASSERT
        MatcherAssert.assertThat(
            db.subjectDao().insert(dummyData),
            CoreMatchers.equalTo(listOf(1L, 2L))
        )
    }

    @Test
    @Throws(InterruptedException::class)
    fun insertSubjectsAndRead(): Unit = runBlocking {
        db.subjectDao().insert(dummyData)

        // ASSERT
        db.subjectDao().getSubjectWithChapters().assertItems(dummyData)
    }
}