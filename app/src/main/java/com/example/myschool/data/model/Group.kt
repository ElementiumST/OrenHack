package com.example.myschool.data.model

data class Group(
        val name: String ="",
        val students: List<Student> = listOf(),
        val schedule: List<Day> = listOf(),
        val curatorId: String = ""
)