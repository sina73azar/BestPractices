package com.example.hiltimpl.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)val id:Int?=null,
    val name:String,
    val text:String,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val date:String=System.currentTimeMillis().toString()
)
