package com.example.myschool.ui.news

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myschool.R
import com.example.myschool.data.model.Post
import com.example.myschool.data.model.getPostIconIdByOwnerType
import com.example.myschool.data.utils.getDateString

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.findViewById(R.id.title)
    private val date: TextView = itemView.findViewById(R.id.date)
    private val poster: ImageView = itemView.findViewById(R.id.poster)
    private val text: TextView = itemView.findViewById(R.id.text)
    private val logo: ImageView = itemView.findViewById(R.id.logo)

    fun bind(post: Post) {
        title.text = post.title
        date.text = getDateString(post.date)
        post.preview.subscribe {
            poster.setImageBitmap(it)
        }
        text.text = post.text[0]
        logo.setImageResource(getPostIconIdByOwnerType(post.ownerType))
    }

}