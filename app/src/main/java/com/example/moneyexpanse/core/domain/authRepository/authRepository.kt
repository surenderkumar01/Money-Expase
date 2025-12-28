package com.example.moneyexpanse.core.domain.authRepository

import com.example.moneyexpanse.core.common.authState
import com.google.firebase.auth.FirebaseUser

interface authRepository {

    suspend fun loginPage(email:String, password:String) : authState<FirebaseUser>
   suspend fun signUpPage(email:String, password:String,name:String) : authState<FirebaseUser>

}