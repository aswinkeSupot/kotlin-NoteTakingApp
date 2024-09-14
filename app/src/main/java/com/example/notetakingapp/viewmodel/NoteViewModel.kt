package com.example.notetakingapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notetakingapp.repository.NoteRepository
import com.example.notetakingapp.roomDatabase.Note
import kotlinx.coroutines.launch

/**
 * View Modle : store and manage UI-related Data seperating the UI-related login from UI Controller(Activity/Fragment)
 */
class NoteViewModel(app: Application, private val noteRepository: NoteRepository) : AndroidViewModel(app) {

    fun addNote(note: Note) = viewModelScope.launch { noteRepository.insertNote(note) }

    fun deleteNote(note: Note) = viewModelScope.launch { noteRepository.deleteNote(note) }

    fun updateNote(note: Note) = viewModelScope.launch { noteRepository.updateNote(note) }

    fun deleteAll() = viewModelScope.launch { noteRepository.deleteAll() }

    fun getAllNote() = noteRepository.getAllNotes()

    fun searchNote(query: String?) = noteRepository.searchNote(query)

}