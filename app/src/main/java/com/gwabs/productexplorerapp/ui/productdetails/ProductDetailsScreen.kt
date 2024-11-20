package com.gwabs.productexplorerapp.ui.productdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.gwabs.productexplorerapp.data.model.Product
import com.gwabs.productexplorerapp.utils.Resource


@Composable
fun ProductDetailsScreen(
    productId: Int,
    viewModel: ProductDetailsViewModel = hiltViewModel()
) {
    val productState by viewModel.getProductById(productId).collectAsState(Resource.Loading())

    when (productState) {
        is Resource.Loading -> CircularProgressIndicator(
            modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
        )
        is Resource.Success -> {
            val product = (productState as Resource.Success<Product>).data
            product?.let {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(it.title, style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Category: ${it.category}", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    AsyncImage(
                        model = it.image,
                        contentDescription = it.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    Text("Price: ₦${it.price}", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(it.description, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
        is Resource.Error -> Text(
            "Error: ${productState.message ?: "Unable to load product details"}",
            modifier = Modifier.padding(16.dp)
        )
    }
}

