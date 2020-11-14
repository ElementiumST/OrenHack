package com.example.myschool.ui.shedule

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.core.view.contains
import androidx.core.view.size
import com.example.myschool.R
import kotlin.math.abs


class PaginationView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private var currentTab: Int = 0
    private var numOfTab: Int = 0
    private val root: View = LayoutInflater.from(context).inflate(R.layout.view_pagination, this)
    private val left = root.findViewById<ImageButton>(R.id.left)!!.also {
        it.isEnabled = false
        it.isClickable = false
        it.setOnClickListener { changeCurrentTab(currentTab - 1) }
    }
    private val right = root.findViewById<ImageButton>(R.id.right)!!.also {
        it.setOnClickListener { changeCurrentTab(currentTab + 1) }
    }
    private lateinit var listener: (from: Int, to: Int)->Unit
    private val radioGroup = root.findViewById<RadioGroup>(R.id.rGroup)

    private fun changeCurrentTab(newTab: Int) {
        if(newTab == 0) {
            left.isEnabled = false
            left.isClickable = false
        } else{
            left.isEnabled = true
            left.isClickable = true
        }
        if(newTab == numOfTab-1){
            right.isEnabled = false
            right.isClickable = false
        } else {
            right.isEnabled = true
            right.isClickable = true
        }
        listener(currentTab, newTab)
        (radioGroup.getChildAt(newTab) as RadioButton).isChecked = true
        currentTab = newTab
    }
    fun setData(numOfTab: Int, listener: (from: Int, to: Int) -> Unit) {
        this.listener = listener
        this.numOfTab = numOfTab

        if(radioGroup.size == 0) {
            for(i in 0 until numOfTab) {
                val btn = RadioButton(context)
                btn.layoutParams = LinearLayout.LayoutParams(left.width, left.height)
                radioGroup.addView(btn)
            }
        }
        radioGroup.setOnCheckedChangeListener { _, id ->
            changeCurrentTab((id-1)%6)
        }
        (radioGroup.getChildAt(0) as RadioButton).isChecked = true

    }
}