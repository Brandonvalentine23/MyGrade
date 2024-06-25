package com.example.gradesearch.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Course::class, Result::class, User::class], version = 1, exportSchema = false)
abstract class GradeSearchDatabase : RoomDatabase() {
    abstract fun GradeSearchDao(): GradeSearchDao

    companion object {
        @Volatile
        private var Instance: GradeSearchDatabase? = null

        fun getDatabase(context: Context): GradeSearchDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context, GradeSearchDatabase::class.java, "gradesystem.db"
                )
                    .createFromAsset("database/gradesystem.db")
                    .build().also {
                        Instance = it
                }
            }
        }
    }
}