package com.example.moneyexpanse.core.domain.authRepository

interface AddIncomeRepo {

    suspend fun addIncome(income: String)
    suspend fun fatchIncome(): String
}