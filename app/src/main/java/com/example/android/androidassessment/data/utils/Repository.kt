package com.example.android.androidassessment.data.utils

import com.example.android.androidassessment.data.local.SubjectWithChapters
import com.example.android.androidassessment.model.DataResponse
import com.example.android.androidassessment.model.Subject
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repository {

    fun getSubjects(): Flow<ResultState<List<SubjectWithChapters>>>

    suspend fun getSubjectsFromWebService(): List<Subject>
}