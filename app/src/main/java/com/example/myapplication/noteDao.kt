package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface noteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note :note)

    @Delete
    suspend fun delete(note : note)

    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes(): LiveData<List<note>>
}