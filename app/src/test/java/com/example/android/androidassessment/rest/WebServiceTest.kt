package com.example.android.androidassessment.rest

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.io.IOException

@RunWith(JUnit4::class)
class WebServiceTest : BaseWebServiceTest() {

    private lateinit var service: WebService

    @Before
    @Throws(IOException::class)
    fun createService() {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create()
    }

    @Test
    @Throws(IOException::class, InterruptedException::class)
    fun getNewsSource() = runBlocking {
        enqueueResponse("content.json")
        val response = service.getSubjects().body() ?: return@runBlocking

        // Dummy request
        mockWebServer.takeRequest()

        // Check response
        assertThat(response, notNullValue())
        assertThat(response.data.status, `is`("success"))
        assertThat(response.data.message, `is`("success"))

        // Check list
        val subjects = response.data.subjects
        assertThat(subjects, notNullValue())

        // Check first subject
        val subject = subjects[0]
        assertThat(subject, notNullValue())
        assertThat(subject.id, `is`(2))
        assertThat(subject.name, `is`("Mathematics"))
        assertThat(subject.chapters[0].id, `is`(151))
        assertThat(subject.chapters[0].name, `is`("Exam Test"))
        assertThat(subject.chapters[0].lessons[0].id, `is`(1168))
        assertThat(subject.chapters[0].lessons[0].name, `is`("dsfjdfkj"))
    }
}