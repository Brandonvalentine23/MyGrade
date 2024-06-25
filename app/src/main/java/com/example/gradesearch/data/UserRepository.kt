package com.example.gradesearch.data

class UserRepository(private val gradeSearchDao: GradeSearchDao) {

    /*suspend fun validateCredentials(email: String, password: String): Boolean {
        val user = gradeSearchDao.loginUser(email, password)
        return user != null && user.password == password
    }*/

    suspend fun updateUser(user: User) {
        gradeSearchDao.updateUser(user)
    }

    suspend fun getUser(userType: String): User? {
        return gradeSearchDao.getUser(userType)
    }

    suspend fun addUser(user: User) {
        gradeSearchDao.addUser(user)
    }
}