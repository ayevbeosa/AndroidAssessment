package com.example.android.androidassessment.data

import com.example.android.androidassessment.data.local.SubjectDao
import com.example.android.androidassessment.data.local.SubjectWithChapters
import com.example.android.androidassessment.data.utils.ResultState
import com.example.android.androidassessment.model.Subject
import com.example.android.androidassessment.rest.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AssessmentRepository @Inject constructor(
    private val subjectDao: SubjectDao,
    private val webService: WebService
) : SubjectMapper {

    fun getSubjects(): Flow<ResultState<List<SubjectWithChapters>>> {
        return flow {
            // Commence loading
            emit(ResultState.loading())

            val latestSubjects = getSubjectsFromWebService()
            if (latestSubjects.isNotEmpty()){
                latestSubjects.toLocal().let {
                    subjectDao.clearAndInsertSubjects(it)
                }
            }

            val savedSubjects = subjectDao.getSubjectWithChapters()
            emitAll(savedSubjects.map { ResultState.success(it) })

        }.flowOn(Dispatchers.IO)
    }

    private suspend fun getSubjectsFromWebService(): List<Subject> {
        try {
            val response = webService.getSubjects()

            if (response.body() != null) {
                return response.body()!!.data.subjects
            }
            return emptyList()

        } catch (t: Throwable) {
            Timber.e(t)
            return emptyList()
        }
    }

}