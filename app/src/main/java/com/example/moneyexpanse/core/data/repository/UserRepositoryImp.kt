package com.example.moneyexpanse.core.data.repository

import android.util.Log
import com.example.moneyexpanse.core.data.dataModel.User
import com.example.moneyexpanse.core.domain.authRepository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(val auth: FirebaseAuth,val firestore: FirebaseFirestore): UserRepository {
            override suspend fun getUser(): List<User> {

                val uid = auth.currentUser?.uid ?: return emptyList()
                val snapshot = firestore.collection("User").whereEqualTo("uid" ,uid).get().await()
                val data = snapshot.documents.mapNotNull {
                    it.toObject(User::class.java)
                }
                Log.d("TAGG",data.firstOrNull()?.name.toString())
                Log.d("TAGG",data.firstOrNull()?.email.toString())
                return data
            }
    override suspend fun LogOut() {
       auth.signOut()
    }
}