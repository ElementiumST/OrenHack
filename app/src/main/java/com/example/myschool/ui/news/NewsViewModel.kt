package com.example.myschool.ui.news

import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myschool.data.model.Post
import com.example.myschool.data.utils.firebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class NewsViewModel : ViewModel() {
    private val newsReference = firebaseDatabase.getReference("news")
    private val newsStorage = FirebaseStorage.getInstance().getReference("news")
    val newsList = MutableLiveData<List<Post>>()
    init {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<Post>()
                for (dataSnapshotListElement in snapshot.children) {
                    val data = dataSnapshotListElement.getValue(Post::class.java)!!
                    list.add(data)
                    newsStorage.child(data.previewLink).getBytes(1024*1024).addOnSuccessListener {
                        val image = BitmapFactory.decodeByteArray(it, 0, it.size)
                        data.preview.onNext(image)
                    }
                }
                newsList.postValue(list)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("DataError", error.message)
            }
        }
        newsReference.addValueEventListener(listener)
    }
}