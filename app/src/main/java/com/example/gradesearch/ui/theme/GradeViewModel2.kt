package com.example.gradesearch.ui.theme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gradesearch.data.GradeRepository2
import com.example.gradesearch.data.GradeSearchDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GradeViewModel2(application: Application) : AndroidViewModel(application) {
    private val repository: GradeRepository2

    val courseName = MutableStateFlow("")
    val username = MutableStateFlow("")
    val grade = MutableStateFlow("")
    val pointer = MutableStateFlow("")

    init {
        val database = GradeSearchDatabase.getDatabase(application)
        val gradeDao = database.GradeSearchDao()
        val userDao = database.GradeSearchDao()
        val courseDao = database.GradeSearchDao()
        repository = GradeRepository2(gradeDao, userDao, courseDao)
    }

    fun resetFields() {
        courseName.value = ""
        username.value = ""
        grade.value = ""
        pointer.value = ""
    }

    fun insertGrade() {
        viewModelScope.launch {
            val pointerFloat = pointer.value.toFloatOrNull()
            if (pointerFloat != null) {
                repository.insertGrade(courseName.value, username.value, grade.value, pointerFloat)
            }
        }
    }
}
