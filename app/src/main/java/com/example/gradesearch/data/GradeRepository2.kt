package com.example.gradesearch.data

import com.example.gradesearch.data.GradeSearchDao

class GradeRepository2(
    private val gradeDao: GradeSearchDao,
    private val userDao: GradeSearchDao,
    private val courseDao: GradeSearchDao
) {
    suspend fun insertGrade(courseName: String, username: String, grade: String, pointer: Float) {
        val user = userDao.getUserByUsername(username)
        val course = courseDao.getCourseByName(courseName)
        if (user != null && course != null) {
            val result = Result(
                pointer = pointer,
                grade = grade,
                courseid = course.courseid,
                userid = user.userid
            )
            gradeDao.insert(result)
        }
    }
}
