package com.kovsheful.signsync.feature_signsync.core.util

sealed class ResourceStatus<T>(val data: T? = null, val message: String? = null) {
    class None<T>: ResourceStatus<T>()
    class Loading<T>(data: T? = null): ResourceStatus<T>(data)
    class Success<T>(data: T?): ResourceStatus<T>(data)
    class Error<T>(message: String, data: T? = null): ResourceStatus<T>(data, message)
}