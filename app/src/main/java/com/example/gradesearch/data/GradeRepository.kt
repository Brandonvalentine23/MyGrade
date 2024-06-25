package com.example.gradesearch.data

class GradeRepository(private val gradeSearchDao: GradeSearchDao) {
    suspend fun getGradesByMatricNumber(): List<Result> {
        return gradeSearchDao.getGradesByMatricNumber()
    }

    suspend fun insertGrade(result: Result) {
        gradeSearchDao.insert(result)
    }

    suspend fun getCourseByName(courseName: String): Course? {
        return gradeSearchDao.getCourseByName(courseName)
    }

    suspend fun getUserByUsername(username: String): User? {
        return gradeSearchDao.getUserByUsername(username)
    }
}