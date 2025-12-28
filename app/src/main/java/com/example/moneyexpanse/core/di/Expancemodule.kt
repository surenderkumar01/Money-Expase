package com.example.moneyexpanse.core.di




import com.example.moneyexpanse.core.data.repository.authRepositoryImp

import com.example.moneyexpanse.core.domain.authRepository.authRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moneyexpanse.core.data.repository.ExpanceRepositoryImp
import com.example.moneyexpanse.core.data.repository.ItemTypeRepositotryImp
import com.example.moneyexpanse.core.data.repository.UserRepositoryImp
import com.example.moneyexpanse.core.domain.authRepository.ExpanceRepository
import com.example.moneyexpanse.core.domain.authRepository.ItemTypeRepositotry
import com.example.moneyexpanse.core.domain.authRepository.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object Expancemodule {


    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideRepository(auth: FirebaseAuth, firestore: FirebaseFirestore):authRepository{
        return authRepositoryImp(auth,firestore)
    }

    @Provides
    @Singleton
    fun provideRepositoryExpance(auth: FirebaseAuth,firestore: FirebaseFirestore): ExpanceRepository{
        return ExpanceRepositoryImp(auth,firestore)
    }

    @Provides
    @Singleton
    fun provideRepositoryAllExpance(auth: FirebaseAuth,firestore: FirebaseFirestore): ItemTypeRepositotry{
        return ItemTypeRepositotryImp(auth,firestore)
    }


    @Provides
    @Singleton
    fun provideRepositoryUser(auth: FirebaseAuth,firestore: FirebaseFirestore): UserRepository{
        return UserRepositoryImp(auth,firestore)
    }


}