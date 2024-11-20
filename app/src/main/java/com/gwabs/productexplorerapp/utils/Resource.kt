package com.gwabs.productexplorerapp.utils

import com.gwabs.productexplorerapp.data.model.Product

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Success<T>(data: T) : Resource<T>(data) // data is guaranteed to be non-null
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}
