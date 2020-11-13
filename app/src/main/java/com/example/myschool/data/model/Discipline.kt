package com.example.myschool.data.model

data class Discipline(
        val teacherId: String = "",
        val name: String = "",
        val ratingStudentIds: List<String> = listOf()

)