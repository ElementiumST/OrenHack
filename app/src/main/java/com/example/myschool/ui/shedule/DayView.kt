package com.example.myschool.ui.shedule

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.lifecycle.observe
import com.example.myschool.R
import com.example.myschool.data.model.Day

class DayView(context: Context) :
    FrameLayout(context) {
    private val listView: LinearLayout
    lateinit var listOfDays: List<Day>
    init {
        val inflater = LayoutInflater.from(context)
        val root = inflater.inflate(R.layout.view_day, this)
        listView = root.findViewById(R.id.list)

    }
    fun setDataSource(data: List<Day>) {
        listOfDays = data
        for (item in listOfDays) {
        }
    }


}