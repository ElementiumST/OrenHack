package com.example.myschool.data.model.alalytics

import java.io.Serializable

data class Appraisal(
        val value: Int = 0,
        val date: Long = 0,
        val reason: String = ""
): Serializable