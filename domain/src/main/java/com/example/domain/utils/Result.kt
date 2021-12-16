package com.example.domain.utils

/**
 * @name Mohamed Mabrouk
 * Copyrights (c) 16/12/2021 created by Just clean
 */
sealed class Result<out T : Any>{
    object Loading : Result<Nothing>()
    data class Success<out T:Any>(val data:T) : Result<T>()
    data class Fail(val error : Throwable) : Result<Nothing>()
    data class NoInternetConnection(val error:String) : Result<Nothing>()
}
