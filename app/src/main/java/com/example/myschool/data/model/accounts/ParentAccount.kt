package com.example.myschool.data.model.accounts

class ParentAccount(
        val groupId: String = "",
        val studentId: String = "",
        override val accountType: Int = 1,
        override val userName: String = "",
        override val password: String = ""
): Account()