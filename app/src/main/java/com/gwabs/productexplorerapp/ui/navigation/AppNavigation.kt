package com.gwabs.productexplorerapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
        composable(
            "product_details/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            ProductDetailsScreen(productId = productId,navController= navController)
        }
    }
}

