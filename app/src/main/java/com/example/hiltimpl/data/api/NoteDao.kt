package com.example.hiltimpl.data.api

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.hiltimpl.domain.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNote(note: Note)

    @Delete
    fun deleteNote(note:Note)

    @Query("SELECT * FROM note_table")
    fun getAllNotes():List<Note>
}