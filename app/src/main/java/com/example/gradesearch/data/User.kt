package com.example.gradesearch.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "user")

data class User(
    @PrimaryKey(autoGenerate = true)
    val userid: Int = 0,
    val username: String,
    val password: String,
    val usertype: String,
    val useremail: String
)