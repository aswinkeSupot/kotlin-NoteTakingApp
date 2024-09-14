package com.example.notetakingapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notetakingapp.repository.NoteRepository

/**
 * If our viewModel has a constructor with parameters we can't use the
 * default constructor that the viewModel framework provides.
 *
 * ViewModelFactory: pass the required parameters to ViewModel
 * **/

class NoteViewModelFactory(val app: Application, private val noteRepository: NoteRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return NoteViewModel(app, noteRepository) as T

        if(modelClass.isAssignableFrom(NoteViewModel::class.java)){
            return NoteViewModel(app, noteRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}