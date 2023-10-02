package com.example.todo

import android.content.ClipData
import com.example.todo.data.TodoItem
import com.google.gson.Gson

class ClipDataConverter {
    companion object{
    fun convertTodoItem(todoItem: TodoItem): ClipData{
        val gson = Gson()
        val todoItemGson = gson.toJson(todoItem)

        val clipData = ClipData.newPlainText("TodoItem", todoItemGson)

        return clipData
    }
    }
}