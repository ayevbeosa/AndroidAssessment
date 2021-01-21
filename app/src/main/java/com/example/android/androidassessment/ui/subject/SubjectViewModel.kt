package com.example.android.androidassessment.ui.subject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.androidassessment.data.local.SubjectWithChapters
import com.example.android.androidassessment.model.SubjectNavigation

class SubjectViewModel(subjectWithChapters: SubjectWithChapters) : ViewModel() {

    private val _selectedSubjectWithChapters = MutableLiveData<SubjectWithChapters>()
    val selectedSubjectWithChapters: LiveData<SubjectWithChapters>
        get() = _selectedSubjectWithChapters

    private val _navigateToSelectedItem = MutableLiveData<SubjectNavigation>()
    val navigateToSelectedItem: LiveData<SubjectNavigation>
        get() = _navigateToSelectedItem

    init {
        _selectedSubjectWithChapters.value = subjectWithChapters
    }

    fun displaySelectedLesson(SubjectNavigation: SubjectNavigation) {
        _navigateToSelectedItem.value = SubjectNavigation
    }

    fun displaySelectedLessonComplete() {
        _navigateToSelectedItem.value = null
    }
}