package com.example.myschool.ui.diary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myschool.R
import com.example.myschool.data.model.alalytics.Journal
import com.example.myschool.data.utils.getDateString
import com.example.myschool.view.InfoCardView

class DiaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)
        val journals: Array<Journal> = intent.extras!!.getSerializable("data") as Array<Journal>
        val list = findViewById<LinearLayout>(R.id.list)
        findViewById<TextView>(R.id.title).text = journals[0].disciplineId
        for(journal in journals) {
            var string = ""
            for(appr in journal.appraisalList) {
                string += "${getDateString(appr.date)}: оценка - ${appr.value}\n" +
                        "${appr.reason},\n\n"
            }
            list.addView(InfoCardView(this,"${journal.fourth} четверть",
                "Оценки:\n$string"))
        }

    }
}