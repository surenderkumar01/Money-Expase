package com.example.moneyexpanse.core.common

sealed  class authState <out T>{
    data class Success<T>(val data:T): authState<T>()
    data class Error(val error:String): authState<Nothing>()
    data object  Idle: authState<Nothing>()
   data object Loading: authState<Nothing>()

}
