package com.example.android.androidassessment.di

import android.app.Application
import com.example.android.androidassessment.data.local.AssessmentDatabase
import com.example.android.androidassessment.data.local.SubjectDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDb(application: Application): AssessmentDatabase {
        return AssessmentDatabase.buildDatabase(application)
    }

    @Singleton
    @Provides
    fun provideSubjectDao(db: AssessmentDatabase): SubjectDao {
        return db.subjectDao()
    }
}