package com.example.myschool

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myschool.data.model.*
import com.example.myschool.data.model.accounts.Account
import com.example.myschool.data.model.accounts.StudentAccount
import com.example.myschool.data.model.alalytics.Analytics
import com.example.myschool.data.model.alalytics.Appraisal
import com.example.myschool.data.model.alalytics.Journal
import com.example.myschool.data.utils.TimeSet
import com.example.myschool.data.utils.firebaseDatabase
import com.example.myschool.data.utils.uploadUtilsData

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uploadUtilsData()
        setContentView(R.layout.activity_main)
        val account = intent.getSerializableExtra("account") as Account
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        if(account.accountType == 0) {
            val studentAccount = account as StudentAccount
            viewModel.uploadData(studentAccount.groupId, studentAccount.studentId)
        }

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_news, R.id.navigation_schedule, R.id.navigation_blog))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

                /*
        firebaseDatabase.getReference("utils").child("timeSet")
                .setValue(listOf(
                        TimeSet("8:00", "8:45"),
                        TimeSet("8:55", "9:40"),
                        TimeSet("9:55", "10:40"),
                        TimeSet("10:55", "11:40"),
                        TimeSet("11:50", "12:35"),
                        TimeSet("12:45", "13:30"),
                        TimeSet("13:40", "14:25"),
                        TimeSet("14:35", "15:20")
                ))




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
                    "Дисциплина ${i}"
            ))

        }
        for(i in 1..11) {
            val ref = firebaseDatabase.getReference("groups").push()
            val students = mutableListOf<Student>()
            val journals = mutableListOf<Journal>()
            for (discipline in disciplineIds) {
                for(fourth in 1..4) {
                    val appraisals = mutableListOf<Appraisal>()
                    for(appraisalNum in 0..(3..10).random()) {
                        appraisals.add(
                            Appraisal(
                                (2..5).random(),
                                (1583153920000..1622465920000).random(),
                                "Причина ${(0..10).random()}"
                            )
                        )
                    }
                    val journal = Journal(
                        fourth/2,
                        fourth,
                        discipline,
                        appraisals

                    )
                    journals.add(journal)
                }
            }

            val analytics = Analytics(
                journals
            )

            for(j in 1..24) {
                val student = Student(
                        "Имя ${j}",
                        "Фамилия ${j}",
                        "Отчество ${j}",
                        (6..8).random()+i,
                        (0..1).random(),
                        analytics

                )
                students.add(student)
            }
            val days = mutableListOf<Day>()
            for (d in 1..6) {
                val lessons = mutableListOf<Lesson>()
                val skip = (0..2).random()
                for (l in 1..6) {

                    val lesson = Lesson(
                        l+skip,
                            disciplineIds[(0 until disciplineIds.size).random()],
                            "Тема урока ${d}.${l}",
                            "Домашнее задание урока ${d}.${l}",
                            (0..15).random().toString(),
                            teacherIds[(1 until teacherIds.size).random()],
                            "Комментарий к уроку ${l}"
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