package com.example.myschool.ui.diary

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.myschool.R

class   AppraisalView(context: Context, value: Float) : CardView(context) {
    init {
        val textView = TextView(context)
        textView.text = value.toString()
        textView.setTextColor(resources.getColor(android.R.color.white))
        addView(textView)
        layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        radius = 4F
        when (value) {
            in 4.5..5.0 -> setCardBackgroundColor(resources.getColor(R.color.green))
            in 3.7..4.5 -> setCardBackgroundColor(resources.getColor(R.color.yellow))
            in 2.9..3.7 -> setCardBackgroundColor(resources.getColor(R.color.orange))
            in 2.0..2.9 -> setCardBackgroundColor(resources.getColor(R.color.red))
        }
    }
} 