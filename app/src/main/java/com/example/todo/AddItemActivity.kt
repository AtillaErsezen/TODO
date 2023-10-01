package com.example.todo

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.example.todo.data.DatabaseManager
import com.example.todo.data.TodoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddItemActivity : AppCompatActivity() {
    private var viewModel: AddItemActivityViewModel? = null
    private var background_layout: ConstraintLayout? = null
    private var line_2: LinearLayout? = null
    private var line_1: LinearLayout? = null
    private var line_3: LinearLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        viewModel = ViewModelProvider(this)[AddItemActivityViewModel::class.java]
        val ok_button: Button = findViewById(R.id.ok_button)

        line_1 = findViewById(R.id.line_1)
        line_2 = findViewById(R.id.line_2)
        line_3 = findViewById(R.id.line_3)
        background_layout = findViewById(R.id.constraint_background)

        dugmeleriKoy()

        val editText: EditText = findViewById(R.id.editTextText)
        val textWatcher = object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel?.bosKontrol(text, editText)
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        }
        editText.addTextChangedListener(textWatcher)

        ok_button.setOnClickListener {
            val text: String = editText.text.toString()
            val backgroundColor: Int = (background_layout!!.background as ColorDrawable).color //FIXME
            val textColor = editText.currentTextColor
            val item = TodoItem(text, backgroundColor,false, textColor)
            CoroutineScope(Dispatchers.IO).launch{
            DatabaseManager.getDatabase()!!.dao.addItem(item)
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun dugmeleriKoy() {
        val colors = arrayOf(
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.CYAN,
            Color.YELLOW,
            Color.parseColor("#A52A2A"),  // Brown
            Color.parseColor("#FFC0CB"),  // Pink
            Color.parseColor("#800080"),  // Purple
            Color.parseColor("#FFA500"),  // Orange
            Color.GRAY,
            Color.WHITE,
            Color.BLACK
        )

        for (i in 0..11){
            val button = Button(this)
            button.id = i
            button.width = 40
            button.height = 10
            button.setBackgroundColor(colors[button.id])
            button.setOnClickListener {
                viewModel?.changeBackground(colors[button.id], background_layout!!)
            }
            when (i) {
                in 0..3 -> {
                    line_1!!.addView(button)
                }
                in 4..7 -> {
                    line_2!!.addView(button)
                }
                else -> {
                    line_3!!.addView(button)
                }
            }
        }
    }
}