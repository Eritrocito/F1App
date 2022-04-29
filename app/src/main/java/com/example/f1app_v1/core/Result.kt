package com.example.f1app_v1.core

sealed class Result<out T> {
    class Loading<out T>: Result<T>() //Es el estado de carga
    data class Success<out T>(val data:T): Result<T>()
    data class Failure(val exception: Exception): Result<Nothing>()

}