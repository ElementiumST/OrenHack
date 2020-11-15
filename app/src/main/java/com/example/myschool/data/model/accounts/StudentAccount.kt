package com.example.myschool.data.model.accounts

import com.google.android.gms.common.internal.AccountType

class StudentAccount(
    val groupId: String = "",
    val studentId: String = "",
    val parentId: String = "",
    override val accountType: Int = 0,
    override val userName: String = "",
    override val password: String = ""
): Account()