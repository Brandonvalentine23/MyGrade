package com.example.gradesearch.ui.theme

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gradesearch.data.GradeSearchDao
import com.example.gradesearch.data.GradeSearchDatabase
import com.example.gradesearch.data.User
import com.example.gradesearch.data.UserRepository
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository

    init {
        val userDao = GradeSearchDatabase.getDatabase(application).GradeSearchDao()
        repository = UserRepository(userDao)
    }

    fun addUser(username: String, password: String, userType: String, userEmail: String, onSuccess: () -> Unit, onError: () -> Unit) {
        val user = User(username = username, password = password, usertype = userType, useremail = userEmail)
        viewModelScope.launch {
            try {
                repository.addUser(user)
                Log.d("ADDUSER_PASS", "Successful.")
                onSuccess()
            } catch (e: Exception) {
                Log.d("ADDUSER_FAIL", "Failed.")
                onError()
            }
        }
    }
}
