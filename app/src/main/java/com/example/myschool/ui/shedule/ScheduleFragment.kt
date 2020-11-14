package com.example.myschool.ui.shedule

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.example.myschool.R
import com.example.myschool.data.model.Lesson


class ScheduleFragment : Fragment() {
    lateinit var viewModel: ScheduleViewModel
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_schedule, container, false)
        viewModel = ScheduleViewModel.buildModel(this, "-MM1tODUT7oaFRb406sF")
        val dayContainer = root.findViewById<FrameLayout>(R.id.dayContainer)
        val paginationView = root.findViewById<PaginationView>(R.id.pagination)
        viewModel.schedule.observe(viewLifecycleOwner) {
            if(it.isEmpty()) return@observe
            paginationView.setData(it.size) { from: Int, to: Int ->
                val dayView = DayView(requireContext())
                dayContainer.removeAllViews()
                dayView.setDataSource(it[to], this::clickOnLesson)
                dayContainer.addView(dayView)
            }
        }
        return root
    }
    private fun clickOnLesson(lesson: Lesson) {
    }

}