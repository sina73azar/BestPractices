package com.example.hiltimpl.data.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.hiltimpl.domain.Student

@Dao
interface StudentDao {

    @Query("SELECT * FROM student_table")
    suspend fun getAllStudents(): List<Student>

    @Insert
    suspend fun insertStudent(student: Student)
}