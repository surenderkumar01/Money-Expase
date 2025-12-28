package com.example.moneyexpanse.core.domain.authRepository

import com.example.moneyexpanse.core.data.dataModel.dataModel

interface ExpanceRepository {


    suspend fun saveExpanse(datamodel: dataModel)
    suspend fun fatchExpanse(): List<dataModel>
    suspend fun fatchTotalExpanse(): String

}