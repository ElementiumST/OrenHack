package com.example.myschool.ui.shedule

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myschool.R
import com.example.myschool.data.model.Lesson
import com.example.myschool.data.utils.getTimeSetListObserver

class LessonView(context: Context) : FrameLayout(context) {
    var name: TextView
    var number: TextView
    var lessonNum: TextView
    var timeSet: TextView
    lateinit var lesson: Lesson
    init {
        val inflater = LayoutInflater.from(context)
        val root = inflater.inflate(R.layout.view_lesson, this)
        name = root.findViewById(R.id.lessonName)
        number = root.findViewById(R.id.lessonNumber)
        lessonNum = root.findViewById(R.id.lessonNum)
        timeSet = root.findViewById(R.id.timeSet)
        root.findViewById<CardView>(R.id.card).also {
            it.setOnClickListener {
                listener(lesson)
            }
        }

    }
    @SuppressLint("SetTextI18n")
    fun setData(lesson: Lesson) {
        this.lesson = lesson
        name.text = lesson.disciplineName
        number.text = "каб. ${lesson.cabinet}"
        getTimeSetListObserver().subscribe {
            lessonNum.text = "${lesson.number} урок"
            timeSet.text = it[lesson.number - 1].timeStart+"-"+it[lesson.number - 1].timeEnd
        }
    }
    lateinit var listener: (lesson: Lesson)->Unit

}