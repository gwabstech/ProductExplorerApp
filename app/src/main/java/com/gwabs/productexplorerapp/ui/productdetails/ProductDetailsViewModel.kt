package com.gwabs.productexplorerapp.ui.productdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gwabs.productexplorerapp.data.model.Product
import com.gwabs.productexplorerapp.data.repository.ProductRepository
import com.gwabs.productexplorerapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private var _productState: StateFlow<Resource<Product>>? = null

    fun getProductById(productId: Int): StateFlow<Resource<Product>> {
        if (_productState == null || _productState!!.value is Resource.Error) {
            _productState = repository.getProductById(productId)
                .stateIn(
                    viewModelScope,
                    SharingStarted.WhileSubscribed(5000),
                    Resource.Loading()
                )
        }
        return _productState!!
    }
}
