package com.example.android.androidassessment.data.utils

sealed class ResultState<T> {

    class Loading<T>: ResultState<T>() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            return true
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }

    data class Success<T>(val data: T): ResultState<T>()

//    data class Error<T>(val message: String) : ResultState<T>()

    companion object {

        fun <T> loading(): ResultState<T> = Loading()

        fun <T> success(data: T): ResultState<T> = Success(data)

//        fun <T> error(message: String): ResultState<T> = Error(message)
    }
}
