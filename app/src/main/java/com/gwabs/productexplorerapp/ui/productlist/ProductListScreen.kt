package com.gwabs.productexplorerapp.ui.productlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.gwabs.productexplorerapp.utils.Resource



@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel = hiltViewModel(),
    onProductClick: (Int) -> Unit
) {
    val productsState by viewModel.products.collectAsState()
    when (productsState) {
        is Resource.Loading -> CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        is Resource.Success -> {
            LazyColumn {
                /*
                items((productsState as Resource.Success<List<Product>>).data) { product ->
                    ListItem(
                        headlineText = { Text(product.title) },
                        secondaryText = { Text("Price: $${product.price}") },
                        leadingContent = {
                            AsyncImage(
                                model = product.image,
                                contentDescription = product.title,
                                modifier = Modifier.size(64.dp)
                            )
                        },
                        modifier = Modifier.clickable { onProductClick(product.id) }
                    )
                }

                 */
            }
        }
        is Resource.Error -> Text("Error: ${productsState.message}")
    }
}
