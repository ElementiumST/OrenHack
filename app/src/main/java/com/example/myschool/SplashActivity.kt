package com.example.myschool

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myschool.data.model.accounts.Account
import com.example.myschool.data.model.accounts.StudentAccount
import com.example.myschool.data.utils.firebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class SplashActivity : AppCompatActivity() {
    companion object {
        val LOGIN_CODE: Int = 2342
    }
    lateinit var cash: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        cash = getSharedPreferences("cashData", MODE_PRIVATE)
        if(cash.contains("AccountId") && cash.contains("AccountType")) {
            val accountId = cash.getString("AccountId", null)!!
            firebaseDatabase.getReference("auth").child(accountId).addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(cash.getInt("AccountType", -1) == 0) {
                            val account  = snapshot.getValue(StudentAccount::class.java)
                            startMainActivity(account!!)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e(error.message, error.details)
                    }

                }
            )
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivityForResult(intent, LOGIN_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == LOGIN_CODE) {
            if(resultCode == RESULT_OK) {
                val account = data!!.getSerializableExtra("account") as Account
                cash.edit().apply {
                    this.putString("AccountId", data.getStringExtra("AccountId"))
                    this.putInt("AccountType", account.accountType)
                    this.apply()
                }
                startMainActivity(account)
                finish()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    fun startMainActivity(account: Account) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("account", account)
        startActivity(intent)
    }
}