package com.example.myschool.data.model

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.example.myschool.R
import com.google.firebase.database.Exclude
import io.reactivex.rxjava3.subjects.PublishSubject

data class Post(
    val title: String = "",
    val date: Long = 0,
    val text: List<String> = listOf(),
    val previewLink: String = "",
    @get:Exclude
    val preview: PublishSubject<Bitmap> = PublishSubject.create(),
    val ownerType: OwnerType = OwnerType.School
)
enum class OwnerType {
    School,
    Teacher,
    Curator
}
fun getPostIconIdByOwnerType(ownerType: OwnerType): Int {
    return when(ownerType){
        OwnerType.School -> R.drawable.ic_school
        OwnerType.Teacher -> R.drawable.ic_teacher
        OwnerType.Curator -> R.drawable.ic_curator
    }
}