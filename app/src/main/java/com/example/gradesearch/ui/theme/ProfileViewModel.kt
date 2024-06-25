package com.example.gradesearch.ui.theme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gradesearch.data.GradeSearchDatabase
import com.example.gradesearch.data.User
import com.example.gradesearch.data.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository

    init {
        val userDao = GradeSearchDatabase.getDatabase(application).GradeSearchDao()
        repository = UserRepository(userDao)
    }

    fun updateUser(user: User, onSuccess: () -> Unit) {
        viewModelScope.launch {
            repository.updateUser(user)
            onSuccess()
        }
    }

    fun getUser(matricNumber: String, onUserFetched: (User?) -> Unit) {
        viewModelScope.launch {
            val user = repository.getUser(matricNumber)
            onUserFetched(user)
        }
    }
}
