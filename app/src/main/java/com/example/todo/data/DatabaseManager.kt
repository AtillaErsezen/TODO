package com.example.todo.data

import android.content.Context
import androidx.room.Room

class DatabaseManager {

    companion object {
    private var database: TodoDatabase? = null
        fun createDatabase(context: Context) {
            database = Room.databaseBuilder(
                context,
                TodoDatabase::class.java,
                "TodoDatabase"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    fun getDatabase(): TodoDatabase?{
        return database
    }
    }
}