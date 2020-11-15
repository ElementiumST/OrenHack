package com.example.myschool.ui.diary

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.myschool.MainActivity
import com.example.myschool.R
import com.example.myschool.data.model.alalytics.Journal

class DiaryFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_diary, container, false)
        val listView = root.findViewById<LinearLayout>(R.id.list)
        (requireActivity() as MainActivity).viewModel.student.observe(viewLifecycleOwner) {
            val journals = mutableMapOf<String, MutableList<Journal>>()
            for(journal in it.analytics.journals) {
                if(journals.containsKey(journal.disciplineId))
                    journals.getValue(journal.disciplineId).add(journal)
                else
                    journals[journal.disciplineId] = mutableListOf(journal)
            }
            for (journal in journals) {
                val view = DisciplineView(requireContext(), journal.value)
                listView.addView(view)
                view.setOnClickListener {
                    val intent = Intent(requireContext(), DiaryActivity::class.java)
                    intent.putExtra("data", journal.value.toTypedArray())
                    //startActivity(intent)
                }
            }

        }
        return root
    }
}