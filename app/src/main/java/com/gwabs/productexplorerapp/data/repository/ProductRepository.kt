package com.gwabs.productexplorerapp.data.repository

import com.gwabs.productexplorerapp.data.api.ProductApi
import com.gwabs.productexplorerapp.data.database.ProductDao
import com.gwabs.productexplorerapp.data.model.Product
import com.gwabs.productexplorerapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val api: ProductApi,
    private val dao: ProductDao
) {
    fun getProducts(): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading())
        try {
            val products = api.getProducts()
            dao.insertAll(products)
            emit(Resource.Success(products))
        } catch (e: Exception) {
            emit(Resource.Error("Failed to fetch products"))
            emitAll(dao.getAllProducts().map { Resource.Success(it) })
        }
    }
}

