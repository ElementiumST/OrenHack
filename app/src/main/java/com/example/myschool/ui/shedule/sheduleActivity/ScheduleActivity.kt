package com.example.myschool.ui.shedule.sheduleActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import com.example.myschool.R
import com.example.myschool.data.model.Lesson
import com.example.myschool.data.model.Teacher
import com.example.myschool.data.utils.firebaseDatabase
import com.example.myschool.ui.shedule.LessonView
import com.example.myschool.view.InfoCardView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ScheduleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        val lesson: Lesson = intent.extras!!.get("data") as Lesson
        val list = findViewById<LinearLayout>(R.id.list)
        val lessonView = LessonView(this)
        lessonView.setData(lesson)
        list.addView(lessonView)
        Log.e("TESTEST", lesson.teacherId)
        firebaseDatabase.getReference("teachers").child(lesson.teacherId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val teacher = snapshot.getValue(Teacher::class.java)
                    val teacherView = InfoCardView(
                        this@ScheduleActivity,
                        "Учитель",
                            teacher!!.lastName +
                                " ${teacher.firstName}" +
                                " ${teacher.middleName}\n" +
                                teacher.qualification
                    )
                    list.addView(teacherView)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(error.message, error.details)
                }

            })
        val theme = InfoCardView(this, "Тема урока", lesson.theme)
        list.addView(theme)
        val homeWork = InfoCardView(this, "Домашнее задание", lesson.homework)
        list.addView(homeWork)
        val comment = InfoCardView(this, "Комметарий к уроку", lesson.comment)
        list.addView(comment)
    }
}