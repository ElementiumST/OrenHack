package com.example.myschool.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.myschool.R


@SuppressLint("ViewConstructor")
class InfoCardView(context: Context, title: String, text: String) : CardView(context) {
    init {
        val root = LayoutInflater.from(context).inflate(R.layout.view_info_card_view, this)
        root.findViewById<TextView>(R.id.title).text = title
        root.findViewById<TextView>(R.id.text).text = text
    }
}