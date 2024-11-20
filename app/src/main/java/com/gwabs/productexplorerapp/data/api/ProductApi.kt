package com.gwabs.productexplorerapp.data.api

import com.gwabs.productexplorerapp.data.model.Product
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): List<Product>
}
