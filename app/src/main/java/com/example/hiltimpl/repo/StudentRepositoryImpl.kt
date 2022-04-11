package com.example.hiltimpl.repo

import com.example.hiltimpl.domain.Student
import com.example.hiltimpl.data.StudentDao
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val studentDao: StudentDao
) {
    suspend fun getAllStudent()=studentDao.getAllStudents()
    suspend fun insertStudent(student: Student)=studentDao.insertStudent(student)
}