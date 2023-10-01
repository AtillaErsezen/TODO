package com.example.todo.data

import android.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TodoDatabase")
data class TodoItem (

    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "color") val backgroundColor: Int,
    @ColumnInfo(name = "done") val done: Boolean,
    @ColumnInfo(name = "text color") val textColor: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = 0

)
