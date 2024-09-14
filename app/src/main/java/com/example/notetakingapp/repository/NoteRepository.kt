package com.example.notetakingapp.repository

import com.example.notetakingapp.roomDatabase.NoteDatabase
import com.example.notetakingapp.roomDatabase.Note

/**
 * Repository : act as a bridge between the ViewModel and the Data Source.
 */
class NoteRepository(private val db: NoteDatabase) {

    suspend fun insertNote(note : Note) = db.getNoteDao().insertNote(note)

    suspend fun deleteNote(note : Note) = db.getNoteDao().deleteNote(note)

    suspend fun updateNote(note : Note) = db.getNoteDao().updateNote(note)

    suspend fun deleteAll() = db.getNoteDao().deleteAll()

    fun getAllNotes() = db.getNoteDao().getAllNotesInDB()

    fun searchNote(query: String?)= db.getNoteDao().searchNote(query)

}