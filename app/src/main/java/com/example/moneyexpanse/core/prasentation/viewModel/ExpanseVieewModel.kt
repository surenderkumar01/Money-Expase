package com.example.moneyexpanse.core.prasentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneyexpanse.core.data.dataModel.dataModel
import com.example.moneyexpanse.core.domain.authRepository.ExpanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.exp


@HiltViewModel
class ExpanseVieewModel @Inject constructor(val expenseRepository: ExpanceRepository) :
    ViewModel() {

    private val _addIExpanseState = mutableStateOf<List<dataModel>>(emptyList())
    val addIExpanseState: State<List<dataModel>> = _addIExpanseState

    private val _TotoalExpanse = mutableStateOf("0.0")
    val TotoalExpanse: State<String> = _TotoalExpanse
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading   // <- Yeh expose karna sahi hai

    init {
        fatchTotalExpanse()
        fatchExpanse()
    }
    fun saveExpanse(datamodel: dataModel) {
        viewModelScope.launch {
            expenseRepository.saveExpanse(datamodel)
        }
    }

    fun fatchExpanse() {
        viewModelScope.launch {
            _isLoading.value = true
            _addIExpanseState.value = expenseRepository.fatchExpanse()
            _isLoading.value = false
        }
    }

    fun fatchTotalExpanse() {
        viewModelScope.launch {
            _TotoalExpanse.value = expenseRepository.fatchTotalExpanse()
        }
    }
}