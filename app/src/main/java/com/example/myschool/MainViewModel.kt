package com.example.myschool

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myschool.data.model.Post
import com.example.myschool.data.model.Student
import com.example.myschool.data.model.alalytics.Analytics
import com.example.myschool.data.model.alalytics.Journal
import com.example.myschool.data.utils.firebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class MainViewModel : ViewModel() {
    val student = MutableLiveData<Student>()
    val groupId = MutableLiveData<String>()
    fun uploadData(groupId: String, studentId: String) {
        this.groupId.postValue(groupId)
        firebaseDatabase.getReference("groups").child(groupId).child("students").child(studentId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    student.postValue(snapshot.getValue(Student::class.java))
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(error.message, error.details)
                }

            })
    }
}