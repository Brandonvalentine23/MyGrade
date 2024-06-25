package com.example.gradesearch.data

class RegisterRepository(private val gradeSearchDao: GradeSearchDao) {
    suspend fun addCourse(user: User) {
        gradeSearchDao.addUser(user)
    }


}
