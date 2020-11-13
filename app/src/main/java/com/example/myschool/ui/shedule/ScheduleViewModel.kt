package com.example.myschool.ui.shedule

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myschool.data.model.Day
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ScheduleViewModel: ViewModel() {
    companion object{
        fun buildModel(scheduleFragment: ScheduleFragment, groupId: String): ScheduleViewModel {
            return ViewModelProvider(scheduleFragment).get(ScheduleViewModel::class.java).also { it.groupId = groupId }
        }
    }
    private val database = FirebaseDatabase.getInstance().getReference("groups")
    val schedule = MutableLiveData<List<Day>>()
    lateinit var groupId: String;
    init {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<Day>()
                for (day in snapshot.children) {
                    list.add(day.getValue(Day::class.java)!!)
                }
                schedule.postValue(list)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Schedule load data", error.message)
            }
        }
        database.child(groupId).child("days").addListenerForSingleValueEvent(listener)
    }
}