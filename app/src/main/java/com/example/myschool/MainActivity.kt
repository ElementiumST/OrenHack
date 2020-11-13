package com.example.myschool

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myschool.data.model.*
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_news, R.id.navigation_schedule, R.id.navigation_blog))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        /*
        val firebaseDatabase = FirebaseDatabase.getInstance()
        val teacherIds = mutableListOf<String>()

        for (i in 1..10) {
            val ref = firebaseDatabase.getReference("teachers").push()
            teacherIds.add(ref.key!!)

            ref.setValue(Teacher(
                    "Иванов ${i}",
                    "Иван ${i}",
                    "Иванович ${i}",
                    "Учитель ${i}"
            ))
        }
        val disciplineIds = mutableListOf<String>()
        for (i in 1..10) {
            val ref = firebaseDatabase.getReference("disciplines").child("Дисциплина ${i}")
            disciplineIds.add(ref.key!!)
            ref.setValue(Discipline(
                    teacherIds[(1 until teacherIds.size).random()],
                    "Дисциплина ${i}"
            ))

        }
        for(i in 1..11) {
            val ref = firebaseDatabase.getReference("groups").push()
            val students = mutableListOf<Student>()
            for(j in 1..24) {
                val student = Student(
                        "Имя ${j}",
                        "Фамилия ${j}",
                        "Отчество ${j}",
                        (6..8).random()+i,
                        (0..1).random()
                )
                students.add(student)
            }
            val days = mutableListOf<Day>()
            for (d in 1..6) {
                val lessons = mutableListOf<Lesson>()
                val skip = (0..2).random()
                for (l in 1..8) {

                    val lesson = Lesson(
                        l+skip,
                            disciplineIds[(0 until disciplineIds.size).random()],
                            "Тема урока ${d}.${l}",
                            "Домашнее задание урока ${d}.${l}",
                            (0..15).random().toString(),
                            teacherIds[(1 until teacherIds.size).random()]
                    )
                    lessons.add(lesson)
                }
                val day = Day(
                        d,
                        lessons
                )
                days.add(day)

            }
            val group = Group(
                    "${i}`A`",
                    students,
                    days,
                    teacherIds[(1 until teacherIds.size).random()]
            );
            ref.setValue(group)
        }

         */
    }
}