package com.example.myschool.data.utils

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject
import java.sql.Time
import java.util.*

private val calendar = Calendar.getInstance()

fun getDateString(ms: Long): String {
    calendar.timeInMillis = ms
    return "${calendar.get(Calendar.DAY_OF_MONTH)} " +
            "${monthNumToString(calendar.get(Calendar.MONTH))}, " +
            "${calendar.get(Calendar.YEAR)}"
}
private fun monthNumToString(int: Int): String {
    return when(int) {
        0 -> "января"
        1 -> "февраля"
        2 -> "марта"
        3 -> "апреля"
        4 -> "мая"
        5 -> "июня"
        6 -> "июля"
        7 -> "августа"
        8 -> "сентября"
        9 -> "октября"
        10 -> "ноября"
        11 -> "декабря"
        else -> "N/A"
    }
}
data class TimeSet(
        val timeStart: String = "",
        val timeEnd: String = ""
)
private val firebaseDatabase = FirebaseDatabase.getInstance()
fun uploadUtilsData() {
    firebaseDatabase.getReference("utils").child("timeSet").addListenerForSingleValueEvent(listener)
}

private val listener = object : ValueEventListener {
    override fun onDataChange(snapshot: DataSnapshot) {
        val list = mutableListOf<TimeSet>()
        for (item in snapshot.children) {
            list.add(item.getValue(TimeSet::class.java)!!)
        }
        dateTable = list
        dateTableObs.doOnSubscribe {
            dateTableObs.onNext(list)
            it.dispose()
        }
        dateTableObs.onNext(list)
    }

    override fun onCancelled(error: DatabaseError) {
        Log.e(error.message, error.details)
    }

}
private var dateTable: List<TimeSet>? = null

val dateTableObs: ReplaySubject<List<TimeSet>> = ReplaySubject.create()
fun getTimeSetListObserver(): ReplaySubject<List<TimeSet>> {
    return dateTableObs.also {
            if(dateTable == null) return@also
            dateTableObs.onNext(dateTable)
    }
}
fun dayNumToTitle(date: Int): String {
    return when(date){
        1 -> "Понедельник"
        2 -> "Вторник"
        3 -> "Среда"
        4 -> "Четверг"
        5 -> "Пятница"
        6 -> "Суббота"
        7 -> "Воскресенье"
        else -> "N/A"
    }
}
val firebase = FirebaseDatabase.getInstance().also { it.setPersistenceEnabled(true) }
