package com.example.gradesearch.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GradeSearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCourse(course: Course)

    @Query("SELECT * FROM course")
    suspend fun getAllCourses(): List<Course>

    @Query("DELETE FROM course WHERE courseid = :courseId")
    suspend fun deleteCourse(courseId: String)

    @Query("SELECT * FROM user WHERE useremail = :email AND password = :password LIMIT 1")
    suspend fun getUser(email: String, password: String): User?

    @Query("SELECT * FROM result")
    fun getAllGrades(): List<Result>

    @Query("SELECT * FROM result")
    suspend fun getGradesByMatricNumber(): List<Result>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM user WHERE usertype = :usertype LIMIT 1")
    suspend fun getUser(usertype: String): User?

    @Insert
    suspend fun addUser(user: User)

    @Insert
    suspend fun insert(result: Result)

    @Query("SELECT * FROM course WHERE coursename = :courseName LIMIT 1")
    suspend fun getCourseByName(courseName: String): Course?

    @Query("SELECT * FROM user WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): User?


}