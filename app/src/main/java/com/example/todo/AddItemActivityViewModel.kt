package com.example.todo

import android.graphics.Color
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText

class AddItemActivityViewModel: ViewModel() {

    fun bosKontrol(text: CharSequence?, editText: EditText) {
        if (text?.trim()?.isEmpty() == true){
            editText.error = "Cannot be empty"
        }
    }

    fun changeBackground(i: Int, layout: ConstraintLayout) {
        layout.setBackgroundColor(i)
        if (i == R.color.black) //siyah siyah üstünde gözükmeyeceği için text color değiştirdik
            layout.findViewById<EditText>(R.id.editTextText).setTextColor(Color.WHITE)
    }

}