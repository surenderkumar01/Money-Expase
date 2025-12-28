package com.example.moneyexpanse.core.domain.authRepository

import com.example.moneyexpanse.core.data.dataModel.dataModel

interface ItemTypeRepositotry {
    suspend fun getTraval():List<dataModel>
    suspend fun getFood():List<dataModel>
    suspend fun getBills():List<dataModel>
    suspend fun getNiji():List<dataModel>
}