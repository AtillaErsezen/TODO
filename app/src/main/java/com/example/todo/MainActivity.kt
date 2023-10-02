package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import com.example.todo.data.DatabaseManager
import com.example.todo.data.Include
import com.example.todo.data.TodoDao
import com.example.todo.data.TodoDatabase
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var linearLayout: LinearLayout? = null
    companion object{
     var copDugmesi: FloatingActionButton? = null
     var toolbar: MaterialToolbar? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linearLayout = findViewById(R.id.linearLayout)
        val fab: FloatingActionButton = findViewById(R.id.floatingActionButton)
        copDugmesi = findViewById(R.id.delete_button)
        toolbar = findViewById(R.id.materialToolbar)

        fab.setOnClickListener{
            val intent = Intent(this, AddItemActivity::class.java)
            startActivity(intent)
        }
        DatabaseManager.createDatabase(this)
        val database: TodoDatabase = DatabaseManager.getDatabase()!!

        val todoDao = database.dao
        CoroutineScope(Dispatchers.IO).launch {
            val itemCount: Int = todoDao.getItemCount()

            if (itemCount > 0) {
                kutulariDiz(todoDao)
            }else{
                findViewById<TextView>(R.id.empty_text).visibility = View.VISIBLE
            }

        }

    }

    private fun kutulariDiz(todoDao: TodoDao) {
        val itemList = todoDao.getAllItems()
        CoroutineScope(Dispatchers.Main).launch {
            for (item in itemList) {
                val include = Include(
                    this@MainActivity,
                    item.text,
                    item.backgroundColor,
                    item.done,
                    item.textColor
                )
                include.tag = item // iliştirdik
                linearLayout?.addView(include)
            }
        }
    }


}