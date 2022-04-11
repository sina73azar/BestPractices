package com.example.hiltimpl.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class Student(
    val name: String,
    @PrimaryKey(autoGenerate = true) var id: Int?=null
)
