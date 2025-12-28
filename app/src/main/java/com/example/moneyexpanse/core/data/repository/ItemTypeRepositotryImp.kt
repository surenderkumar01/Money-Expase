package com.example.moneyexpanse.core.data.repository

import android.util.Log
import com.example.moneyexpanse.core.data.dataModel.dataModel
import com.example.moneyexpanse.core.domain.authRepository.ItemTypeRepositotry
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ItemTypeRepositotryImp @Inject constructor(
    val auth: FirebaseAuth,
    val firestore: FirebaseFirestore
) : ItemTypeRepositotry {
    override suspend fun getTraval(): List<dataModel> {

        val uid = auth.currentUser?.uid ?: return emptyList()
        val snapshot =
            firestore.collection("Expance").whereEqualTo("uid", uid).whereEqualTo("type", "Travel")
                .get().await()
        val data = snapshot.documents.mapNotNull {
            it.toObject(dataModel::class.java)
        }
        return data
    }

    override suspend fun getFood(): List<dataModel> {

        val uid = auth.currentUser?.uid ?: return emptyList()
        val snapshot = firestore.collection("Expance").whereEqualTo("uid", uid).whereEqualTo("type", "Food").get().await()
        val data = snapshot.documents.mapNotNull {
            it.toObject(dataModel::class.java)
        }
        return data
    }

    override suspend fun getBills(): List<dataModel> {

        val uid = auth.currentUser?.uid ?: return emptyList()
        val snapshot = firestore.collection("Expance").whereEqualTo("uid", uid).whereEqualTo("type", "Bills").get().await()

        snapshot.documents.forEach {
            Log.d("DOC_DATA", it.data.toString()) // check actual field names
        }
        val data = snapshot.documents.mapNotNull {
            it.toObject(dataModel::class.java)
        }
        return data
    }

    override suspend fun getNiji(): List<dataModel> {

        val uid = auth.currentUser?.uid ?: return emptyList()
        val snapshot =
            firestore.collection("Expance").whereEqualTo("uid", uid).whereEqualTo("type", "Shopping").get().await()
        val data = snapshot.documents.mapNotNull {
            it.toObject(dataModel::class.java)
        }
        return data
    }
}