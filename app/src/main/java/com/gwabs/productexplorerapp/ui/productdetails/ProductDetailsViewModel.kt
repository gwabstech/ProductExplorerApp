package com.gwabs.productexplorerapp.ui.productdetails

import androidx.lifecycle.ViewModel
import com.gwabs.productexplorerapp.data.model.Product
import com.gwabs.productexplorerapp.data.repository.ProductRepository
import com.gwabs.productexplorerapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {
    fun getProductById(id: Int): Flow<Resource<out Any>> {
        return repository.getProducts()
            .map { resource ->
                when (resource) {
                    is Resource.Success<*> -> {
                        val product = resource.data?.find { it.id == id }
                        if (product != null) Resource.Success(product) else Resource.Error("Product not found")
                    }
                    else -> resource
                }
            }
    }
}
