package com.example.moneyexpanse.core.prasentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneyexpanse.core.data.dataModel.dataModel
import com.example.moneyexpanse.core.domain.authRepository.ItemTypeRepositotry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class AllDataViewModel @Inject constructor(val itemTypeRepositotry: ItemTypeRepositotry) : ViewModel() {
    private val _addIAllExpanseState = mutableStateOf<List<dataModel>>(emptyList())
    val addIAllExpanseState: State<List<dataModel>> = _addIAllExpanseState

    val isLoading=mutableStateOf(false)

      fun getTraval(){
          viewModelScope.launch {

              isLoading.value=true
            _addIAllExpanseState.value=  itemTypeRepositotry.getTraval()

              isLoading.value=false
          }
    }

    fun Shopping(){
        viewModelScope.launch {

            isLoading.value=true
            _addIAllExpanseState.value=  itemTypeRepositotry.getNiji()
            isLoading.value=false
        }
    }

    fun getFood(){
        viewModelScope.launch {

            isLoading.value=true
            _addIAllExpanseState.value=itemTypeRepositotry.getFood()
            isLoading.value=false
        }
    }


    fun getBills(){
        viewModelScope.launch {

            isLoading.value=true
            _addIAllExpanseState.value=itemTypeRepositotry.getBills()
            isLoading.value=false
        }
    }




}