package com.example.movieapp.core

// A generic class that contains data and status about loading this data.
sealed class Resource<T> {
    data class Progress<T>(val isLoading: Boolean) : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Failure<T>(val message: String?, val code: Int? = null) : Resource<T>()
}
