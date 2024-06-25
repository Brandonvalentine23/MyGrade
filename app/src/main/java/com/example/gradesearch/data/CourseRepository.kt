package com.example.gradesearch.data

class CourseRepository(private val gradeSearchDao: GradeSearchDao) {
    suspend fun addCourse(course: Course) {
        gradeSearchDao.addCourse(course)
    }

    suspend fun getAllCourses(): List<Course> {
        return gradeSearchDao.getAllCourses()
    }

    suspend fun deleteCourse(courseId: String) {
        gradeSearchDao.deleteCourse(courseId)
    }
}
