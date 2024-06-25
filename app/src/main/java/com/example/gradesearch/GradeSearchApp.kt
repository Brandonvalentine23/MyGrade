
package com.example.gradesearch

import android.app.Application
import com.example.gradesearch.data.GradeSearchDatabase
import com.example.gradesearch.data.UserRepository

class GradeSearchApp : Application() {
    lateinit var userRepository: UserRepository
    private val database: GradeSearchDatabase by lazy { GradeSearchDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        // Initialize UserRepository with the DAO from the database
        userRepository = UserRepository(database.GradeSearchDao())
    }
}