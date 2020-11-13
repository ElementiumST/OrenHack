package com.example.myschool.ui.shedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.myschool.R

class ScheduleFragment : Fragment() {
    lateinit var viewModel: ScheduleViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_schedule, container, false)
        viewModel = ScheduleViewModel.buildModel(this, "-MM0_g_epDXHR5cXPnJg")
        val dayView = root.findViewById<DayView>(R.id.dayView)
        viewModel.schedule.observe(viewLifecycleOwner) {
            dayView.setDataSource(it)
        }
        return root
    }
}