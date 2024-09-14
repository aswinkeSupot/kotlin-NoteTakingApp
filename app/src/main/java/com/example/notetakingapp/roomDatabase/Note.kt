package com.example.notetakingapp.roomDatabase

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Created by Aswin on 04-09-2024.
 */
/**
 * ROOM DB Details
 * Entity (TableName) : notes
 * 1. note_id
 * 2. note_title
 * 3. note_body
 * **/
@Entity(tableName = "notes_table")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    val id : Int,
    @ColumnInfo(name = "note_title")
    val noteTitle : String,
    @ColumnInfo(name = "note_body")
    val noteBody : String
):Parcelable
