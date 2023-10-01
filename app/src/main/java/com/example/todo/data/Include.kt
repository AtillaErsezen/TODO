package com.example.todo.data

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.toColor
import com.example.todo.MainActivityViewModel
import com.example.todo.R

class Include(
    context: Context,
    text: String,
    backgroundColor: Int,
    done: Boolean,
    textColor: Int

): ConstraintLayout(context) {

    init{
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.todo_item, findViewById(R.id.todo_constraint))

        view.findViewById<TextView>(R.id.todo_textView).text = text
        view.findViewById<TextView>(R.id.todo_textView).setTextColor(textColor)
        val constraint_background = view.findViewById<ConstraintLayout>(R.id.todo_background)
        constraint_background.setBackgroundColor(backgroundColor)
        constraint_background.setOnLongClickListener{
            //TODO arkaplan rengi değişik mavi olsun
            //TODO çöp düğmesi görünür olsun
            //TODO seçilen kutuları listeye at
            true
        }
        addView(view)
    }

}