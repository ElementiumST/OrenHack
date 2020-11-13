package com.example.myschool.data.model

data class Student(
    val firstName: String = "",
    val middleName: String = "",
    val lastName: String = "",
    val age: Int = 0,
    val sex: Int = 0,
    val analytics: Analytics = Analytics()
)

