package com.example.myschool.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.myschool.MainActivity
import com.example.myschool.R
import com.example.myschool.data.model.Post

class NewsAdapter() : RecyclerView.Adapter<NewsViewHolder>() {
    var items: List<Post> = listOf()
    lateinit var parent: ViewGroup
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        this.parent = parent
        val inflater = LayoutInflater.from(parent.context)
        val holderRoot = inflater.inflate(R.layout.holder_post, parent, false)
        return NewsViewHolder(holderRoot)
    }

    fun setData(list: List<Post>) {
        items = list
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(items[position])
    }
}