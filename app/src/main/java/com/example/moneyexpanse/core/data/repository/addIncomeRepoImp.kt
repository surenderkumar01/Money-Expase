package com.example.moneyexpanse.core.data.repository

import android.util.Log
import com.example.moneyexpanse.core.domain.authRepository.AddIncomeRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.lang.reflect.Field
import javax.inject.Inject

class addIncomeRepoImp @Inject constructor( val auth: FirebaseAuth,val firestore: FirebaseFirestore): AddIncomeRepo {
    override suspend fun addIncome(income: String) {
        val uid = auth.currentUser?.uid

      try {
          firestore.collection("income").document(uid.toString()).update ("income" , FieldValue.increment(income.toLong())).await()
      }catch (e: Exception){
          firestore.collection("income").document(uid.toString()).set (mapOf("income" to income.toLong() )).await()
      }
    }

    override suspend fun fatchIncome(): String {
        val uid = auth.currentUser?.uid
        return try {
           val snap= firestore.collection("income").document(uid.toString()).get().await()
            val data=snap.getLong("income")?:"0"
            Log.d("TAGDATA_Uid","$data"?:"-1")

            snap.getLong("income").toString()


        }catch (e: Exception){
            "Error"

        }
    }
}