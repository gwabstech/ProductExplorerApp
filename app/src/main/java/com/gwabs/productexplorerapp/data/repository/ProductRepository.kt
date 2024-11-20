package com.gwabs.productexplorerapp.data.repository

import android.util.Log
import com.gwabs.productexplorerapp.data.api.ProductApi
import com.gwabs.productexplorerapp.data.database.ProductDao
import com.gwabs.productexplorerapp.data.model.Product
import com.gwabs.productexplorerapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
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

    /**
     * Fetches products from the API or local database.
     * Logs success or failure for API and database operations.
     */
    fun getProducts(): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading()) // Emit loading state
        try {
            // Attempt to fetch products from the API
            val products = api.getProducts()
            Log.i("ProductRepository", "Successfully fetched ${products.size} products from API")

            // Save fetched products to the local database
            dao.insertAll(products)
            Log.i("ProductRepository", "Successfully saved products to the database")

            // Emit success state with fetched products
            emit(Resource.Success(products))
        } catch (e: Exception) {
            // Log API fetch failure
            Log.e("ProductRepository", "Failed to fetch products from API: ${e.message}", e)

            // Emit error state
            emit(Resource.Error("Failed to fetch products from API"))

            // Attempt to fetch products from the local database
            dao.getAllProducts().collect { cachedProducts ->
                if (cachedProducts.isNotEmpty()) {
                    Log.i("ProductRepository", "Loaded ${cachedProducts.size} products from the database")
                    emit(Resource.Success(cachedProducts))
                } else {
                    Log.w("ProductRepository", "No cached products found in the database")
                }
            }
        }
    }

    fun getProductById(productId: Int): Flow<Resource<Product>> = flow {
        try {
            emit(Resource.Loading())
            val productFlow = dao.getProductById(productId)
            emitAll(productFlow.distinctUntilChanged().map { Resource.Success(it) })
        } catch (e: Exception) {
            Log.e("ProductRepository", "Error fetching product by ID: ${e.message}", e)
            emit(Resource.Error("Failed to fetch product details"))
        }
    }

}
