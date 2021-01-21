package com.example.android.androidassessment.rest

import com.example.android.androidassessment.model.DataResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Describes endpoints to fetch subjects.
 */
interface WebService {

    /**
     * Get subjects
     */
    @GET("content/grade")
    suspend fun getSubjects(): Response<DataResponse>
}