package com.example.mynotes

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {

    val repository: NoteRepository
    val allNotes: LiveData<List<Note>>

    init{
        val dao  = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }
    fun deleteNote(note:Note) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }
    fun insertNote(note:Note) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }
    fun updateNote(note:Note) = viewModelScope.launch(Dispatchers.IO){
        repository.update(note)
    }

}

