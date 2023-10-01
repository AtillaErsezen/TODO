package com.example.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoItem::class], version = 2, exportSchema = false)
abstract class TodoDatabase: RoomDatabase() {

    abstract val dao: TodoDao
}