package com.example.gradesearch.ui.theme

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gradesearch.data.GradeSearchDatabase
import com.example.gradesearch.data.Course
import com.example.gradesearch.data.CourseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CourseViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CourseRepository

    init {
        val gradeSearchDao = GradeSearchDatabase.getDatabase(application).GradeSearchDao()
        repository = CourseRepository(gradeSearchDao)
    }

    fun addCourse(courseid: String, coursename: String, lecturer: String, departmentname: String, academicyear: String, userid: String, onSuccess: () -> Unit) {
        // Convert academicyear and userid from String to Int
        val academicYearInt = academicyear.toIntOrNull() ?: 0 // Default to 0 if conversion fails
        val userIdInt = userid.toIntOrNull() ?: 0 // Default to 0 if conversion fails

        val course = Course(courseid, coursename, lecturer, departmentname, academicYearInt, userIdInt)
        viewModelScope.launch {
            try {
                repository.addCourse(course)
            } catch (e: Exception) {
                Log.e("ADDCOURSE_ERROR_VM", "ERROR ENCOUNTERED: $e")
            }
            onSuccess()
        }
    }

    fun getAllCourses(onCoursesFetched: (List<Course>) -> Unit) {
        viewModelScope.launch {
            val courses = repository.getAllCourses()
            onCoursesFetched(courses)
        }
    }

    fun deleteCourse(courseId: String) {
        viewModelScope.launch {
            repository.deleteCourse(courseId)
        }
    }
}
