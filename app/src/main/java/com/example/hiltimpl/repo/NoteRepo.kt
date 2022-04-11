package com.example.hiltimpl.repo

import com.example.hiltimpl.data.NoteDao
import com.example.hiltimpl.domain.Note
import javax.inject.Inject

class NoteRepo @Inject constructor(
    private val noteDao: NoteDao
) {
    suspend fun insertNote(note: Note){
        noteDao.insertNote(note)
    }

    fun deleteNote(note:Note){
        return noteDao.deleteNote(note)
    }

    fun getAllNotes():List<Note>{
        return noteDao.getAllNotes()
    }
}