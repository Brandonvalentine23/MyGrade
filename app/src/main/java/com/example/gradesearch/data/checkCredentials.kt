package com.example.gradesearch.data

import android.content.Context
import com.example.gradesearch.data.GradeSearchDatabase

suspend fun checkCredentials(context: Context, email: String, password: String): Boolean {
    val userDao = GradeSearchDatabase.getDatabase(context).GradeSearchDao()
    val user = userDao.getUser(email, password)
    return user != null
}
