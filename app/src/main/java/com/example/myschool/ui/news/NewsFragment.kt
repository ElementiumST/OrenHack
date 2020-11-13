package com.example.myschool.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myschool.R

class NewsFragment : Fragment() {
    lateinit var viewModel: NewsViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        val recyclerView: RecyclerView = root.findViewById(R.id.list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = NewsAdapter()
        viewModel.newsList.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        recyclerView.adapter = adapter
        return root
    }
}