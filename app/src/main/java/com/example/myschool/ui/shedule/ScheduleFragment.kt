package com.example.myschool.ui.shedule

import android.annotation.SuppressLint
import android.content.Intent
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
import com.example.myschool.MainActivity
import com.example.myschool.R
import com.example.myschool.data.model.Lesson
import com.example.myschool.ui.shedule.sheduleActivity.ScheduleActivity


class ScheduleFragment : Fragment() {
    lateinit var viewModel: ScheduleViewModel
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_schedule, container, false)
        (requireActivity() as MainActivity).viewModel.groupId.observe(requireActivity()) {
            viewModel = ScheduleViewModel.buildModel(this, it)
            val dayContainer = root.findViewById<FrameLayout>(R.id.dayContainer)
            val paginationView = root.findViewById<PaginationView>(R.id.pagination)
            viewModel.schedule.observe(viewLifecycleOwner) { schedule ->
                if(it.isEmpty()) return@observe
                paginationView.setData(schedule.size) { from: Int, to: Int ->
                    val dayView = DayView(requireContext())
                    dayContainer.removeAllViews()
                    dayView.setDataSource(schedule[to], this::clickOnLesson)
                    dayContainer.addView(dayView)
                }
            }
        }

        return root
    }
    private fun clickOnLesson(lesson: Lesson) {
        val intent = Intent(requireActivity(), ScheduleActivity::class.java)
        intent.putExtra("data", lesson)
        startActivity(intent)
    }

}