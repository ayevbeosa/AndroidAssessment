package com.example.android.androidassessment.ui.dashboard

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.android.androidassessment.data.AssessmentRepository
import com.example.android.androidassessment.data.local.SubjectWithChapters
import com.example.android.androidassessment.data.utils.ResultState

class DashboardViewModel
@ViewModelInject constructor(assessmentRepository: AssessmentRepository) : ViewModel() {

    private val _subjectWithChaptersLiveData: LiveData<ResultState<List<SubjectWithChapters>>> =
        assessmentRepository.getSubjects().asLiveData()

    val subjects get() = _subjectWithChaptersLiveData

    private val _navigateToSelectedItem = MutableLiveData<SubjectWithChapters>()
    val navigateToSelectedItem: LiveData<SubjectWithChapters>
        get() = _navigateToSelectedItem

    fun displaySelectedSubject(subjectWithChapters: SubjectWithChapters) {
        _navigateToSelectedItem.value = subjectWithChapters
    }

    fun displaySelectedSubjectComplete() {
        _navigateToSelectedItem.value = null
    }
}