package com.example.myschool.ui.shedule

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myschool.R
import com.example.myschool.data.model.Day
import com.example.myschool.data.model.Lesson
import com.example.myschool.data.utils.dayNumToTitle

class DayView(context: Context) :
        FrameLayout(context) {
    private val listView: LinearLayout
    private val dayView: TextView
    lateinit var day: Day
    init {
        val inflater = LayoutInflater.from(context)
        val root = inflater.inflate(R.layout.view_day, this)
        listView = root.findViewById(R.id.list)
        dayView = root.findViewById(R.id.day)
    }

    @SuppressLint("ResourceAsColor")
    fun setDataSource(day: Day, listener: (lesson: Lesson)->Unit) {
        this.day = day
        for (item in day.lessons.indices) {
            val lessonView = LessonView(context)
            lessonView.setData(day.lessons[item])
            lessonView.listener = listener
            listView.addView(lessonView)

        }
        dayView.text = dayNumToTitle(day.date)

    }


}