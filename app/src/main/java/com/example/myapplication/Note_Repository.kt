package com.example.myapplication

import androidx.lifecycle.LiveData

class Note_Repository (private val noteDao: noteDao){

    val allNotes :LiveData<List<note>> = noteDao.getAllNotes()
    suspend fun insert(note: note){
        noteDao.insert(note)
    }
    suspend fun delete(note: note){
        noteDao.delete(note)
    }

}