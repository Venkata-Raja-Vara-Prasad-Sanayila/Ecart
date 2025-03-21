package com.venkata.org.model.commons

sealed class ApiState<out R> {

    data class Success<T>(val data: T): ApiState<T>()
    data class Error(val message: String): ApiState<Nothing>()
   // data object Loading: ApiState<Nothing>()
}