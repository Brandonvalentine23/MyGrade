package com.example.gradesearch.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "result",
    foreignKeys = [
        ForeignKey(
            entity = Course::class,
            parentColumns = ["courseid"],
            childColumns = ["courseid"]
        ),
        ForeignKey(
            entity = User::class,
            parentColumns = ["userid"],
            childColumns = ["userid"]
        )
    ]
)

data class Result(
    @PrimaryKey(autoGenerate = true)
    val resultid: Int = 0,
    val pointer: Float,
    val grade: String,
    val courseid: String,
    val userid: Int
)


