package com.example.myschool.data.model.accounts

import java.io.Serializable

//0 - student
//1 - teacher
//2 - parent
abstract class Account: Serializable {
    abstract val accountType: Int
    abstract val userName: String
    abstract val password: String
}