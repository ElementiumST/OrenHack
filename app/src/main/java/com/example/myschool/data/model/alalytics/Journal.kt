package com.example.myschool.data.model.alalytics

import java.io.Serializable

data class Journal(
    val halfOfYear: Int = 0,
    val fourth: Int = 0,
    val disciplineId: String = "",
    val appraisalList: List<Appraisal> = listOf()
): Serializable