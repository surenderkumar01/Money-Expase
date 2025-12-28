package com.example.moneyexpanse.core.domain.authRepository

import com.example.moneyexpanse.core.data.dataModel.User

interface UserRepository {

    suspend fun getUser(): List<User>
    suspend fun LogOut()
}