package com.example.todo

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
//TODo silme modu ekle
    companion object {
    fun setState(state: MainActivityState) {
        if (state == MainActivityState.NORMAL) {

        } else if (state == MainActivityState.SILME) {
            MainActivity.copDugmesi?.visibility = View.VISIBLE
            MainActivity.toolbar?.visibility = View.VISIBLE
            val secildi_yazi = MainActivity.toolbar?.findViewById<TextView>(R.id.secildi)
            val cancel_yazi = MainActivity.toolbar?.findViewById<TextView>(R.id.cancel)
            cancel_yazi?.setOnClickListener{
                setState(MainActivityState.NORMAL)
            }
            secildi_yazi?.visibility = View.VISIBLE
            cancel_yazi?.visibility = View.VISIBLE
            val itemList = IncludeManager.getAll()
            for (item in itemList){
                item.setOnClickListener{
                    item.setBackgroundColor(
                        Color.BLUE)
                }
            }

        }
    }
}
}