package com.example.myschool.data.model

data class Day(
    val date: Int = 0,
    val lessons: List<Lesson> = listOf()
)