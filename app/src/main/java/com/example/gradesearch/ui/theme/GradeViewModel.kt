package com.example.gradesearch.ui.theme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gradesearch.data.GradeSearchDatabase
import com.example.gradesearch.data.Result
import com.example.gradesearch.data.GradeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GradeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: GradeRepository

    private val _grades = MutableStateFlow<List<Result>>(emptyList())
    val grades: StateFlow<List<Result>> = _grades

    init {
        val gradeSearchDao = GradeSearchDatabase.getDatabase(application).GradeSearchDao()
        repository = GradeRepository(gradeSearchDao)
    }

    fun loadGrades() {
        viewModelScope.launch {
            _grades.value = repository.getGradesByMatricNumber()
        }
    }
}
