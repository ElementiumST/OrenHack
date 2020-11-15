package com.example.myschool.data.model

import com.example.myschool.data.model.alalytics.Analytics
import com.example.myschool.data.utils.firebaseDatabase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Exclude

data class Student(
    val firstName: String = "",
    val middleName: String = "",
    val lastName: String = "",
    val age: Int = 0,
    val sex: Int = 0,
    val analytics: Analytics = Analytics(),
    @get:Exclude
    var reference: DatabaseReference = firebaseDatabase.getReference()
)


