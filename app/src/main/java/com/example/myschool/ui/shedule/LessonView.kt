package com.example.myschool.ui.shedule

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.example.myschool.R
import com.example.myschool.data.model.Lesson

class LessonView(context: Context) : FrameLayout(context) {
    var name: TextView
    var number: TextView
    init {
        val inflater = LayoutInflater.from(context)
        val root = inflater.inflate(R.layout.view_lesson, this)
        name = root.findViewById(R.id.lessonName)
        number = root.findViewById(R.id.lessonNumber)
    }
    fun setData(lesson: Lesson) {
       // name.text = lesson.
    }
}