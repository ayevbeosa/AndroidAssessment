package com.example.android.androidassessment.ui.subject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.androidassessment.data.local.SubjectWithChapters

class SubjectViewModelFactory(
    private val subjectWithChapters: SubjectWithChapters
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubjectViewModel::class.java)) {
            return SubjectViewModel(subjectWithChapters) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}