package com.gwabs.productexplorerapp.ui.productlist


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.gwabs.productexplorerapp.data.model.Product
import com.gwabs.productexplorerapp.utils.Resource


@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel = hiltViewModel(),
    onProductClick: (Int) -> Unit
) {
    val productsState by viewModel.products.collectAsState()

    when (productsState) {
        is Resource.Loading -> CircularProgressIndicator(
            modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
        )
        is Resource.Success -> {
            val products = (productsState as Resource.Success<List<Product>>).data
            LazyColumn {
                items(products ?: emptyList()) { product ->
                    ProductCard(product = product, onClick = { onProductClick(product.id) })
                }
            }
        }
        is Resource.Error -> Text(
            text = productsState.message ?: "An error occurred",
            modifier = Modifier.fillMaxSize(),
        )
    }

}


@Composable
private fun ProductCard(product: Product,onClick: () -> Unit){
    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(10.dp),

    ){
        Column (
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(300.dp)

                ,
            verticalArrangement = Arrangement.Center
        ){
            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

                Text(
                    text = product.title.split(" ").take(3).joinToString(" "),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 4.dp)
                )


            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = product.category,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "₦${product.price}",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}