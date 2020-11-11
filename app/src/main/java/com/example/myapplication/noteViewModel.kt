package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class noteViewModel(application: Application):AndroidViewModel(application) {
    val allNotes :LiveData<List<note>>
   private  val repository: Note_Repository
    init{
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = Note_Repository(dao)
        allNotes = repository.allNotes
    }
    fun deleteNode(note : note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }
    fun addNote(note: note)=viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }
}