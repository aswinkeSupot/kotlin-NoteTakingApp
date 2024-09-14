package com.example.notetakingapp.roomDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/**
 * DAO : Data Access Object, defines the methods to interact with DB
 *
 * suspend : is use for creating coroutines.
 *
 * @Insert : annotation is used by Room DB Library to identify that the associated
 *  method is used for inserting data into the database.
 *
 * @Update : This annotation is use to identify that the associated method is
 *  used for updating data
 *
 * @Delete : This annotation is use to identify that the associated method is
 *  used for delete item
 *
 * @Query : Is use for custom Query, we can use placeholders like id to pass
 *  parameters to our query
 */

@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    // Delete all data from the table "notes_table" with a custom Query
    @Query("DELETE FROM NOTES_TABLE")
    suspend fun deleteAll()

    // Get all data from the notes_table
    @Query("SELECT * FROM NOTES_TABLE ORDER BY NOTE_ID DESC")
    fun getAllNotesInDB(): LiveData<List<Note>>

    // Search with title and body
    @Query("SELECT * FROM NOTES_TABLE WHERE note_title LIKE :query OR  note_body LIKE :query")
    fun searchNote(query: String?) : LiveData<List<Note>>

}