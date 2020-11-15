package com.example.myschool.data.model

import com.example.myschool.data.model.alalytics.Analytics

data class Student(
    val firstName: String = "",
    val middleName: String = "",
    val lastName: String = "",
    val age: Int = 0,
    val sex: Int = 0,
    val analytics: Analytics = Analytics()
)

