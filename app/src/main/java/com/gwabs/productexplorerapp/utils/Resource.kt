package com.gwabs.productexplorerapp.utils

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    // Loading state
    class Loading<T>(data: T? = null) : Resource<T>(data)

    // Success state
    class Success<T>(data: T) : Resource<T>(data)

    // Error state
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}