package com.example.hiltimpl.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hiltimpl.data.api.NoteDao
import com.example.hiltimpl.data.api.StudentDao
import com.example.hiltimpl.domain.Note
import com.example.hiltimpl.domain.Student

@Database(entities = [Student::class, Note::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao
    abstract fun noteDao(): NoteDao

    companion object{
        const val DATABASE_NAME="main_db"
    }
}