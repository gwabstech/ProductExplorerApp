package com.gwabs.productexplorerapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gwabs.productexplorerapp.ui.productdetails.ProductDetailsScreen
import com.gwabs.productexplorerapp.ui.productlist.ProductListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "product_list") {
        composable("product_list") {
            ProductListScreen { productId ->
                navController.navigate("product_details/$productId")
            }
        }
        composable("product_details/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toInt() ?: 0
            ProductDetailsScreen(productId)
        }
    }
}
