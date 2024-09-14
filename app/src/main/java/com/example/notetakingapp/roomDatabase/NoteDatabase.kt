package com.example.notetakingapp.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @Database - is use to declare a class as a room database and configure its properties.
 */

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao() :NoteDAO

    /**
     * Singleton Design Pattern:
     * Only one instance of the database exists, avoiding unnecessary overhead
     * associated with repeated database creation
     *
     * companion object : define a static singleton instance of this DB Class
     *
     * @Volatile : prevents any possible race conditions in multithreading.
     * **/

    companion object{
        @Volatile
        private var INSTANCE: NoteDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?:
            synchronized(LOCK){
                INSTANCE ?:
                createDatabase(context).also{
                    INSTANCE = it
                }
            }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "note_db"
        ).build()
    }
}