package com.gwabs.productexplorerapp.ui.productdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.gwabs.productexplorerapp.data.model.Product
import com.gwabs.productexplorerapp.utils.Resource

@Composable
fun ProductDetailsScreen(
    id: Int,
    viewModel: ProductDetailsViewModel = hiltViewModel()
) {
    val productState by viewModel.getProductById(id).collectAsState(Resource.Loading())

    when (productState) {
        is Resource.Loading -> CircularProgressIndicator()
        is Resource.Success -> {
            val product = (productState as Resource.Success<Product>).data
            Column(modifier = Modifier.padding(16.dp)) {
                if (product != null) {
                    Text(product.title, style = MaterialTheme.typography.h5)
                }
                if (product != null) {
                    Text("Price: $${product.price}", style = MaterialTheme.typography.body1)
                }
                if (product != null) {
                    AsyncImage(
                        model = product.image,
                        contentDescription = product.title,
                        modifier = Modifier.fillMaxWidth().height(200.dp)
                    )
                }
                if (product != null) {
                    Text(product.description, style = MaterialTheme.typography.body2)
                }
            }
        }
        is Resource.Error -> Text("Error: ${productState.message}")
    }
}
