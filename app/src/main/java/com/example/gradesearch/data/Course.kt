package com.example.gradesearch.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "course",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["userid"],
            childColumns = ["userid"]
        )
    ]
)
data class Course(
    @PrimaryKey(autoGenerate = false)
    val courseid: String,
    val coursename: String,
    val lecturer: String,
    val departmentname: String,
    val academicyear: Int,
    val userid: Int
)
