package com.example.myschool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.myschool.data.model.Student
import com.example.myschool.data.model.accounts.StudentAccount
import com.example.myschool.data.utils.firebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            firebaseDatabase.getReference("auth").addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val username = findViewById<EditText>(R.id.name).text.toString()
                        val password = findViewById<EditText>(R.id.password).text.toString()
                        for(item in snapshot.children)
                            if(item.child("userName").getValue(String::class.java) == username
                                && item.child("password").getValue(String::class.java) == password){
                                if(item.child("accountType").getValue(Int::class.java) == 0) {
                                    val intent = Intent()
                                    intent.putExtra("AccountId", item.key)
                                    intent.putExtra("account", item.getValue(StudentAccount::class.java)!!)
                                    setResult(RESULT_OK, intent)
                                    finish()
                                }
                            }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e(error.message, error.details)
                    }

                }
            )
        }

    }
}