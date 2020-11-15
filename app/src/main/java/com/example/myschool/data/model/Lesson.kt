package com.example.myschool.data.model

import java.io.Serializable

data class Lesson(
        val number: Int = 0,
        val disciplineName: String = "",
        val theme: String = "",
        val homework: String = "",
        val cabinet: String = "",
        val teacherId: String = "",
        val comment: String = ""
) : Serializable