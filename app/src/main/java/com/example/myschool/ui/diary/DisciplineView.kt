package com.example.myschool.ui.diary

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginTop
import androidx.core.view.setMargins
import com.example.myschool.R
import com.example.myschool.data.model.alalytics.Appraisal
import com.example.myschool.data.model.alalytics.Journal
import com.example.myschool.data.utils.getAppraisalByList

@SuppressLint("ResourceAsColor", "ViewConstructor", "SetTextI18n")
class DisciplineView(context: Context, private val journalPack: List<Journal>) : FrameLayout(context) {
    init {
        val root = LayoutInflater.from(context).inflate(R.layout.view_discipline, this)
        findViewById<TextView>(R.id.name).text = journalPack[0].disciplineId
        val list = findViewById<LinearLayout>(R.id.list)

        var sum = 0f
        for (i in journalPack){
            val layout = FrameLayout(context)
            layout.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                topMargin = 8
            }
            val textView = TextView(context)
            textView.text = "${i.fourth} четверть"
            layout.addView(textView)
            val value = getRoundedAppraisal(i.appraisalList)
            sum += value
            val appraisalView = AppraisalView(context, value)
            appraisalView.layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                gravity = Gravity.END
            }
            layout.addView(appraisalView)
            list.addView(layout)
        }
        findViewById<FrameLayout>(R.id.socket).addView(AppraisalView(context, (sum/4.0f).toString().substring(0, 3).toFloat()))


    }
    fun getRoundedAppraisal(index: List<Appraisal>): Float {
        return getAppraisalByList(index).toString().substring(0, 3).toFloat()
    }
}