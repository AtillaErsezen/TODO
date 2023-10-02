package com.example.todo.data

import android.content.ClipData
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.toColor
import com.example.todo.ClipDataConverter
import com.example.todo.MainActivityState
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
        val alt_cizgi = view.findViewById<LinearLayout>(R.id.alt_cizgi)
        view.findViewById<TextView>(R.id.todo_textView).text = text
        view.findViewById<TextView>(R.id.todo_textView).setTextColor(textColor)
        val drag_button = view.findViewById<Button>(R.id.drag_button)
        setOnDragListener{ v, e ->
            when (e.action){
                DragEvent.ACTION_DROP -> {
                    //TODO burada kutuyu alta ekle altı mavi olsun
                    alt_cizgi.setBackgroundColor(Color.TRANSPARENT)

                }
                DragEvent.ACTION_DRAG_LOCATION -> {
                    alt_cizgi.setBackgroundColor(Color.BLUE)

                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    alt_cizgi.setBackgroundColor(Color.TRANSPARENT)

                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    alt_cizgi.setBackgroundColor(Color.TRANSPARENT)
                }
            }
            true
        }
        drag_button.setOnLongClickListener{
            val data = tag as TodoItem
            val shadowBuilder = DragShadowBuilder(view)
            val clipData = ClipDataConverter.convertTodoItem(data)
            view.startDragAndDrop(clipData, shadowBuilder, view, 0)
            true
        }
        val constraint_background = view.findViewById<ConstraintLayout>(R.id.todo_background)
        constraint_background.setBackgroundColor(backgroundColor)
        constraint_background.setOnLongClickListener{
            MainActivityViewModel.setState(MainActivityState.SILME)
            constraint_background.setBackgroundColor(Color.BLUE)
            //TODO arkaplan rengi değişik mavi olsun
            //TODO çöp düğmesi görünür olsun
            //TODO seçilen kutuları listeye at
            true
        }
        alt_cizgi.setOnLongClickListener{
            if (alt_cizgi.solidColor != Color.BLUE) //ikili dokunmaya karşı önlem
            constraint_background.setBackgroundColor(Color.BLUE)

            true
        }
        addView(view)
    }

}