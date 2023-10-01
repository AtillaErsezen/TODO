package com.example.todo.data

import android.graphics.Color
import android.text.style.BackgroundColorSpan
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {

    @Insert
    fun addItem(item: TodoItem)

    @Delete
    fun deleteItem(item: TodoItem)

    @Query("SELECT COUNT(*) FROM TodoDatabase")
    fun getItemCount(): Int

    @Query("SELECT * FROM TodoDatabase")
    fun getAllItems(): List<TodoItem>
}